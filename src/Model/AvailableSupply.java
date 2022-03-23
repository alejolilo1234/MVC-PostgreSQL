package Model;

public class AvailableSupply {
    private String product;
    private String supply;
    private String supplier;
    private double priceToday;
    private int quantity;
    private String description;
    private int total;

    public AvailableSupply() {
        //
    }

    public AvailableSupply(String product, String supply, String supplier, double priceToday, int quantity, String description, int total) {
        this.product = product;
        this.supply = supply;
        this.supplier = supplier;
        this.priceToday = priceToday;
        this.quantity = quantity;
        this.description = description;
        this.total = total;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPriceToday() {
        return priceToday;
    }

    public void setPriceToday(double priceToday) {
        this.priceToday = priceToday;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AvailableSupply{" +
                "product='" + product + '\'' +
                ", supply='" + supply + '\'' +
                ", supplier='" + supplier + '\'' +
                ", priceToday=" + priceToday +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", total=" + total +
                '}';
    }
}
