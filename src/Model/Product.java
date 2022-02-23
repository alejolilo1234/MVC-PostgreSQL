package Model;

public class Product {
    private int id;
    private String code;
    private String name;
    /*private double price;
    private double quantity;*/

    public Product() {
        //
    }

    public Product(int id, String code, String name, double price, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        /*this.price = price;
        this.quantity = quantity;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }*/
}
