//SRP - Bad Example
class Employee{
    private String name;
    private double salary;

    Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
    //This method violates the Single Responsibility Principle (SRP) because it is responsible for both employee data and tax calculation.
    public double calculateTax(){
        return salary - (salary * 0.2); // Example tax calculation
    }

    //This method also violates the SRP because it is responsible for saving employee data to the database.
    public void saveToDatabase(){
        System.out.println("Saving employee data to database...");
    }

}

public class SRP_Bad {
    public static void main(String[] args) {
       Employee emp = new Employee("John Doe", 50000);
       System.out.println("Employee Name: " + emp.getName());
       System.out.println("Employee Salary: " + emp.getSalary());
       System.out.println("Employee Tax: " + emp.calculateTax());
       emp.saveToDatabase();
    }
}