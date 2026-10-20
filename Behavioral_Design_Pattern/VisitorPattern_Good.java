package Behavioral_Design_Pattern;

interface Item {
    public void accept(ItemVisitor visit);
}

class PhysicalProduct implements Item {
    private String productName;
    private double price;

    public PhysicalProduct(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProductName() {
        return this.productName;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }

}

class DigitalProduct implements Item {
    private String productName;
    private double price;

    public DigitalProduct(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProductName() {
        return this.productName;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }

}

interface ItemVisitor {
    public void visit(PhysicalProduct physicalProduct);

    public void visit(DigitalProduct digitalProduct);
}

class GenerateInvoice implements ItemVisitor {

    @Override
    public void visit(PhysicalProduct product) {
        double total = product.getPrice() + 100;

        System.out.println(
                "Physical Product: " + product.getProductName()
                        + ", Total: " + total);
    }

    @Override
     public void visit(DigitalProduct product) {
        double total = product.getPrice();

        System.out.println(
            "Digital Product: " + product.getProductName()
            + ", Total: " + total
        );
    }
}

// element(product) and visitor(main logic)
public class VisitorPattern_Good {
    public static void main(String[] args) {
        Item item = new PhysicalProduct("iPhone", 40000);
        item.accept(new GenerateInvoice());

        Item item2 = new DigitalProduct("ebook", 300);
        item2.accept(new GenerateInvoice());
    }
}
