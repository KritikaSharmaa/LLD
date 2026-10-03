package Creational_Design_Pattern;

// example of singleton design pattern - EAGER LOADING - Object is created at the time of class loading
//Thread safe - no need to implement synchronized method
//However, everytime the class is loaded, the instance is created even if it might not be used in the application, resulting in resource wastage.

// class Employee {

//     private static Employee EmpInstance = new Employee();

//     private Employee() {
//     }

//     public static Employee getInstance() {
//         return EmpInstance;
//     }
// }

//Example of singleton design pattern - LAZY LOADING - Object is created at the time of first request
//This not thread safe - if multiple threads access the getInstance() method simultaneously, multiple instances of the class can be created. To make it thread safe, we can use synchronized keyword in the getInstance() method. However, this will reduce the performance of the application as synchronized method is costly in terms of performance.
class Employee {

    private static volatile Employee EmpInstance; // volatile keyword ensures that multiple threads handle the EmpInstance variable correctly when it is being initialized to the Employee instance.

    private Employee() {
    }

    public static Employee getInstance() {
        if (EmpInstance == null) { // check if instance is null before entering synchronized block to avoid unnecessary synchronization once the instance is created
            synchronized (Employee.class) {
                if (EmpInstance == null) { // double check locking - to avoid multiple threads creating multiple instances of the class, it will check again if the instance is null before creating the instance in case multiple threads are waiting for the lock and the first thread has already created the instance.
                    EmpInstance = new Employee();
                }
            }
        }
        return EmpInstance;
    }
}


//Example of singleton design pattern - LAZY LOADING - Object is created at the time of first request
//Thread safe - no need to implement synchronized method
//******************This is the best approach to implement singleton design pattern***********************
// class Employee {

//     private Employee() {
//     }

//     private static class SingletonHelper {
//         private static final Employee EmpInstance = new Employee();
//     }

//     public static Employee getInstance() {
//         return SingletonHelper.EmpInstance;
//     }
// }

public class SingletonDesign {
    public static void main(String[] args) {
        Employee empInstance = Employee.getInstance();
        Employee empInstance2 = Employee.getInstance();

        System.out.println(empInstance);
        System.out.println(empInstance2);
    }
}
