package Behavioral_Design_Pattern;

interface PaymentStrategy{
    void pay(double amount);
}

class UPIPayment implements PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("UPI Payment of amount Rs."+ amount+" Done.");
    }
}

class CardPayment implements  PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Card Payment of amount Rs."+ amount+" Done.");
    }
}

class CashPayment implements  PaymentStrategy{
    @Override
    public void pay(double amount) {
        System.out.println("Cash Payment of amount Rs."+ amount+" Done.");
    }
}

class CheckoutService{
    PaymentStrategy paymentStrategy;

    public CheckoutService(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount){
        paymentStrategy.pay(amount);
    }
}


public class StrategyPattern {
    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService(new CashPayment());
        checkout.pay(500);
    }
}
