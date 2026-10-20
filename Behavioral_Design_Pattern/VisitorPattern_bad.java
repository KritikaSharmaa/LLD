package Behavioral_Design_Pattern;

import java.util.*;

class PhysicalProduct {
    public void generateInvoice() {
        System.out.println("Invoice generated for Physical Product");
    }

    public double calculateShippingCost() {
        System.out.println("Calculate shipping cost for Physical Product");
        return 10;
    }
}

class DigitalProduct {
    public void generateInvoice() {
        System.out.println("Invoice generated for Digital Product");
    }
}

class GiftCard {
    // Method to print invoice for gift card
    void generateInvoice() {
        System.out.println("Printing invoice for Gift Card...");
    }

    // Method to calculate discount for gift card
    double calculateDiscount() {
        System.out.println("Calculating discount for Gift Card...");
        return 5.0; // Example discount
    }
}

//Issues: Violation of OCP and SRP, Tight coupling between Products and client in future if we need to add more functionalities we need to make changes everywhere
public class VisitorPattern_bad {
    public static void main(String[] args) {
        List<Object> Cart = Arrays.asList(new PhysicalProduct(), new DigitalProduct(), new GiftCard());

        for (Object item : Cart) {
            if (item instanceof PhysicalProduct) {
                ((PhysicalProduct) item).generateInvoice();
                ((PhysicalProduct) item).calculateShippingCost();
            }else if(item instanceof DigitalProduct){
                ((DigitalProduct)item).generateInvoice();
            }else{
                ((GiftCard)item).generateInvoice();
                ((GiftCard)item).calculateDiscount();
            }
        }
    }
}
