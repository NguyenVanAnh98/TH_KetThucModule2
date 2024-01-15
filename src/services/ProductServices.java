package services;

import model.Product;
import utils.FileUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductServices implements Serializable {
    public static List<Product> products = new ArrayList<>();
    private static int currentId = 0;
    public ProductServices(){

    }
    public static List<Product> getProducts() {
        return products;
    }

    public void updateProducts(List<Product> list){
        ProductServices.products = list;
        // tinh toan lai currentID
    }
    public void readFromFile(){
        List<Product> list = FileUtils.readData("./data/products.csv", Product.class);
        ProductServices.products =  list;

        // tim max ID de cap nhat cho ProductService
        Product max = list.stream().max((o1, o2) -> o1.getId() - o2.getId()).get();
        ProductServices.currentId = max.getId();

    }
    public void writeToFile(){
        FileUtils.writeData("./data/products.csv", ProductServices.products);
    }
    public void createProduct(Product product) {
        product.setId(++currentId);
        products.add(product);
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void updateProduct(int productId, String newName, double description, float newPrice, String newQuantity) {
        for (Product product : products) {
            if (product.getId() == productId) {
                product.setName(newName);
                product.setDecription(String.valueOf(description));
                product.setPrice(newPrice);
                product.setQuantity(Integer.parseInt(newQuantity));
                System.out.println("Sản phẩm đã được cập nhật.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm.");
    }
    public void deleteProduct(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                products.remove(product);
                System.out.println("Sản phẩm đã được xoá.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm.");
    }
    public Product searchProduct() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return null;
        }

        Product mostExpensiveProduct = Collections.max(products, Comparator.comparingDouble(Product::getPrice));
        System.out.println("Sản phẩm có giá đắt nhất là:");
        System.out.println(mostExpensiveProduct);
        return mostExpensiveProduct;
    }
    public void sortProductsByPriceAscending() {
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice));
        System.out.println("Sản phẩm đã được sắp xếp theo giá tăng dần.");
    }
    public void sortProductsByPriceDescending() {
        Collections.sort(products, Comparator.comparingDouble(Product::getPrice).reversed());
        System.out.println("Sản phẩm đã được sắp xếp theo giá giảm dần.");
    }

}