interface Invoice{
    public double processInvoice(int amount);
}
//This design adheres to the Open/Closed Principle (OCP) because we can add new regions by creating new classes that implement the Invoice interface, without modifying existing code.
class USInvoice implements Invoice {
    @Override
    public double processInvoice(int amount) {
        return amount + (amount * 0.1); // 10% tax for US
    }
}

class EUInvoice implements Invoice {
    @Override
    public double processInvoice(int amount) {
        return amount + (amount * 0.2); // 20% tax for EU
    }
}

public class OCP_Good{
    public static void main(String[] args) {
        Invoice usInvoice =  new USInvoice();
        System.out.println("US Tax: " + usInvoice.processInvoice(1000));

        Invoice euInvoice =  new EUInvoice();
        System.out.println("EU Tax: " + euInvoice.processInvoice(1000));
    }
}