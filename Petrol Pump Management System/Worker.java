public class Worker {
    private String name;
    private String duty;
    private int hoursWorked;
    private double hourlyRate;

    // Constructor with input validation
    public Worker(String name, int hoursWorked, double hourlyRate) {
        setName(name);
        setDuty("Carwash"); // Default duty set to "Carwash"
        setHoursWorked(hoursWorked);
        setHourlyRate(hourlyRate);
    }

    // Getter and Setter Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (!name.matches("^[a-zA-Z\\s]+$")) { // Only letters and spaces allowed
            throw new IllegalArgumentException("Name can only contain letters and spaces.");
        }
        this.name = name.trim();
    }


    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        if (duty == null || duty.trim().isEmpty()) {
            throw new IllegalArgumentException("Duty cannot be null or empty.");
        }
        if (!duty.equalsIgnoreCase("Carwash") && !duty.equalsIgnoreCase("Petrol Refill")) {
            throw new IllegalArgumentException("Duty must be either 'Carwash' or 'Petrol Refill'.");
        }
        this.duty = duty.trim();
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > 168) { // Maximum possible hours in a week
            throw new IllegalArgumentException("Hours worked must be between 0 and 168.");
        }
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        if (hourlyRate > 10000) { // Maximun hourly rate
            throw new IllegalArgumentException("Hourly rate is unrealistically high.");
        }
        this.hourlyRate = hourlyRate;
    }

    // Method to calculate salary
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }

    // Method to display worker information
    public void displayWorkerInfo() {
        System.out.println("Worker Name: " + name);
        System.out.println("Duty: " + duty);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Hourly Rate: PKR " + hourlyRate);
        System.out.println("Total Salary: PKR " + calculateSalary());
    }
}
