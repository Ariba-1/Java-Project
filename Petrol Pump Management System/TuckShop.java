// Tuckshop class
class Tuckshop {
    private String items;
    private int totalItems;
    private double pricePerItem;

    public Tuckshop(String items, int totalItems) {
        this.items = items;
        this.totalItems = totalItems;
        this.pricePerItem = getPricePerItem(items);
    }

    private double getPricePerItem(String item) {
        switch (item) {
            case "Biscuit": return 50.0;
            case "Bubble": return 10.0;
            case "Cold_Drink": return 80.0;
            case "Choclate": return 100.0;
            default: return 0.0;
        }
    }

    public double calculateBill() {
        return totalItems * pricePerItem;
    }

    public String getItems() {
        return items;
    }

    public int getTotalItems() {
        return totalItems;
    }
}
