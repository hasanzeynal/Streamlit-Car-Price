package az.turbo.main;

public class Car {
    private final String brand;
    private final String price;
    private final String details;

    public Car(String brand, String price, String details) {
        this.brand = brand;
        this.price = price;
        this.details = details;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }
}
