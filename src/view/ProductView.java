package view;

import model.Product;
import services.ProductServices;
import utils.FileUtils;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private Scanner scanner = new Scanner(System.in);
    static ProductServices productServices = new ProductServices();

    public void run() {
        int choice = 0;
        boolean flag = false;
        // viet menu
        do {
            System.out.println("CHUONG TRINH QUAN LY SAN PHAM");
            System.out.println("1. Xem danh sach");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhap");
            System.out.println("4. Xoa");
            System.out.println("5. Sap xep");
            System.out.println("6. Tim kiem san pham co gia tri dat nhat");
            System.out.println("7. Doc tu file");
            System.out.println("8. Ghi vao file");
            System.out.println("9. Thoat");

//        Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1: {
                    displayProduct();

                    break;
                }

                case 2: {
                    addProduct();
                    break;
                }

                case 3: {
                    updateProduct();
                    break;
                }

                case 4: {
                    deleteProduct();
                    break;
                }
                case 5: {
                    sortProduct();

                    break;
                }
                case 6: {
                    searchProduct();
                    break;
                }
                case 7: {
                    readData();
//
                    break;
                }
                case 8: {
                    writeFile();
                    break;
                }
                default:
                    System.out.println("Chức năng không hợp lệ. Vui lòng thử lại.");
                    break;
            }

        } while (!flag);


    }

    private void writeFile() {
        FileUtils.writeData("./data/products.csv", ProductServices.products);
    }


    private void readData() {
        List<Product> list = FileUtils.readData("./data/products.csv", Product.class);
        ProductServices.products =  list;
    }

    public void addProduct() {

        System.out.print("Nhập ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập Tên: ");
        String name = scanner.nextLine();
        System.out.println("Nhập mô tả");
        String description = scanner.nextLine();
        System.out.print("Nhập Giá: ");
        float price = scanner.nextFloat();
        System.out.print("Nhập Số lượng: ");
        int quantity = scanner.nextInt();
        Product newProduct = new Product(id, name, price, quantity, description);
        productServices.createProduct(newProduct);
    }

    public void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xoá: ");
        int deleteId = scanner.nextInt();
        productServices.deleteProduct(deleteId);
    }

    public void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Nhập Tên mới: ");
        String newName = scanner.nextLine();
        System.out.println("Nhập mô tả mới");
        String description = scanner.nextLine();
        System.out.print("Nhập Giá mới: ");
        double newPrice = scanner.nextDouble();
        System.out.print("Nhập Số lượng mới: ");
        int newQuantity = scanner.nextInt();
        productServices.updateProduct(updateId, newName, newPrice, newQuantity, description);
    }

    public void displayProduct() {

        List<Product> products = productServices.getProducts();
        System.out.printf("%10s | %20s | %30s | %15s | %10s\n", "ID", "Name", "Price", "Quantity","description");
        for (Product p : products) {
            System.out.printf("%10s | %20s | %30s | %15s | %10s\n",
                    p.getId(), p.getName(), p.getDecription(), p.getPrice(), p.getQuantity());
        }

    }

    public void sortProduct() {
        System.out.println("Chọn cách sắp xếp giá:");
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        System.out.print("Chọn: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();
        switch (sortChoice) {
            case 1:
                productServices.sortProductsByPriceAscending();
                break;
            case 2:
                productServices.sortProductsByPriceDescending();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    public void searchProduct() {
        productServices.searchProduct();
    }
}

