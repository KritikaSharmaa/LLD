class InvoiceProcessor {
    public double processInvoice(String region, int amount) {
        // Why this violates the Open/Closed Principle (OCP): Imagine this class has been tested and deployed, 
        // and now we need to add support for a new region. We would have to modify this class, which violates the OCP. 
        // Instead, we should be able to extend the functionality without modifying existing code.
       if(region.equals("US")) {
            return amount + (amount * 0.1); // 10% tax for US
       } else if (region.equals("EU")) {
            return amount + (amount * 0.2); // 20% tax for EU
       } else {
            throw new IllegalArgumentException("Region not supported");
       }
    }
}

public class OCP_Bad {
    public static void main(String[] args) {
       InvoiceProcessor invoiceProcessor = new InvoiceProcessor();
       double usTax = invoiceProcessor.processInvoice("US", 1000);
       System.out.println("US Tax: " + usTax);
    }
}
