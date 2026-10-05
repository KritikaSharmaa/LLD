package Structural_Design_Pattern;
interface PaymentGateway {
    public void pay(int orderId, double amount);
}

class PayU implements PaymentGateway {
    @Override
    public void pay(int orderId, double amount) {
        System.out.println("Amount of " + amount + " Rs. payed through PayU");
    }
}

// legacy --> need to integrate with existing code - problem as structure is diff from whats already defined(paymentGateway)
// solution --> use Adapter Pattern
class RazorPay{
    public void payment(int paymentId, double amount){
        System.out.println("Amount of " + amount + " Rs. payed through Razorpay");
    }
}

class AdapterPattern implements PaymentGateway{
    RazorPay razorPay;

    public AdapterPattern(){
        this.razorPay = new RazorPay();
    }

    @Override
    public void pay(int orderId, double amount) {
        razorPay.payment(orderId, amount);
    }
}

class CheckoutService {
    PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void processPayment(int orderid, double amount) {
        paymentGateway.pay(orderid, amount);
    }
}

public class AdapterDesignPattern {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService(new AdapterPattern());
        checkoutService.processPayment(1, 100);
    }
}
