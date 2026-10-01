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
}

class TaxCalculator {
    public double calculateTax(double salary){
        return salary - (salary * 0.2);
    }
}

class EmployeeRepository {
    public void saveToDatabase(Employee employee){
        System.out.println("Saving employee data to database...");
    }
}

public class SRP_Good {
    public static void main(String[] args){
        Employee emp = new Employee("John Doe", 50000);

        TaxCalculator taxCalculator = new TaxCalculator();
        taxCalculator.calculateTax(emp.getSalary());
        
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.saveToDatabase(emp);
    }
}
