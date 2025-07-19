public class CarWash {
    private String carName;
    private String washType;
    private int carModel;

    // Constants for wash prices
    static final int FULL_WASH_PRICE = 2500;
    static final int INNER_WASH_PRICE = 1200;
    static final int OUTER_WASH_PRICE = 1000;

    // Constructor
    public CarWash(String carName, String washType, int carModel) {
        this.carName = carName;
        setWashType(washType); // Use setter for validation
        setCarModel(carModel); // Use setter for validation
    }

    // Getters and setters with validation
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getWashType() {
        return washType;
    }

    public void setWashType(String washType) {
        if (washType.equalsIgnoreCase("full") ||
                washType.equalsIgnoreCase("inner") ||
                washType.equalsIgnoreCase("outer")) {
            this.washType = washType.toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid wash type. Valid types are: full, inner, outer.");
        }
    }

    public int getCarModel() {
        return carModel;
    }

    public void setCarModel(int carModel) {
        if (carModel >= 1800 || carModel == 2024) {
            this.carModel = carModel;
        } else {
            throw new IllegalArgumentException("only 1800 to 2024 car models are allowed.");

        }
    }

    // Method to display wash type and price
    public void displayCarWashType() {
        switch (washType) {
            case "full":
                System.out.println("Full car wash: " + FULL_WASH_PRICE + " 2500 ");
                break;
            case "inner":
                System.out.println("Inner wash: " + INNER_WASH_PRICE + " 1200 ");
                break;
            case "outer":
                System.out.println("Outer wash: " + OUTER_WASH_PRICE + " 1000 ");
                break;
            default:
                System.out.println("Wash type not set.");
                break;
        }
    }

    // Method to display all car wash details
    public void displayCarWashInfo() {
        System.out.println("Car Name: " + carName);
        System.out.println("Car Model: " + carModel);
        System.out.println("Wash Type: " + washType);
        displayCarWashType();
    }
}
