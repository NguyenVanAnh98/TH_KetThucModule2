package model;

public class Product implements IParser<Product> {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String decription;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity, String decription) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.decription = decription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", decription='" + decription + '\'' +
                '}';
    }


    @Override
    public Product parse(String line) {
        String[] items = line.split(",");
        //(int id, String name, double price, int quantity, String decription)
        Product product = new Product(Integer.parseInt(items[0]), items[1], Double.parseDouble(items[2]),Integer.parseInt(items[3]),items[4] );
        return product;
    }
}
