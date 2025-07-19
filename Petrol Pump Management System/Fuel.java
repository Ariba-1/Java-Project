class Fuel {
    protected double pricePerLiter;

    // Constructor for Fuel (protected to prevent direct instantiation)
    protected Fuel() {
    }

    // Method to calculate price based on quantity
    public double calculatePrice(double quantityInLiters) {
        return pricePerLiter * quantityInLiters;
    }
}

// Subclass: Petrol
class Petrol extends Fuel {
    public Petrol() {
        // Set fixed price for petrol
        this.pricePerLiter = 243.00;
    }
}

// Subclass: Diesel
class Diesel extends Fuel {
    public Diesel() {
        // Set fixed price for diesel
        this.pricePerLiter = 261.00;
    }
}
