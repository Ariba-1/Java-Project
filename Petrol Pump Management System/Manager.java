public class Manager {

    private int managerId;

    private static final int DEFAULT_MANAGER_ID = 1234; // Improved constant naming


    public void setManagerId(int managerId) {
        if (managerId == DEFAULT_MANAGER_ID) {
            this.managerId = managerId;
        } else {
            throw new IllegalArgumentException("Please enter a correct ID.");
        }
    }
}



