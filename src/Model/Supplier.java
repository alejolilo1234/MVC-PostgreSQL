package Model;

public class Supplier {
    private String name;
    private String supply;
    private Double price;
    private String description;

    public Supplier() {
        //
    }

    public Supplier(String name, String supply, Double price, String description) {
        this.name = name;
        this.supply = supply;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", supply='" + supply + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
