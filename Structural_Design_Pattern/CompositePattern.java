package Structural_Design_Pattern;

import java.util.*;

// Interface for items that can be added to the cart
interface CartItem {
    double getPrice();

    void display(String indent);
}

class Product implements CartItem {
    String pname;
    double price;

    Product(String pname, double price) {
        this.pname = pname;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void display(String indent) {
        System.out.println(indent + "Product Name: " + this.pname + " and price is: " + this.price);
    }
}

class ProductBundle implements CartItem {
    private String bundleName;
    private List<CartItem> products = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void add(Product p) {
        products.add(p);
    }

    public double getPrice() {
        double amt = 0;
        for (CartItem prod : products) {
            amt += prod.getPrice();
        }

        return amt;
    }

    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName);
        for (CartItem item : products) {
            item.display(indent + "-");
        }
    }
}

class Cart {
    List<CartItem> cartItems = new ArrayList<>();

    public void addItem(CartItem item) {
        cartItems.add(item);
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        Product p = new Product("iphone", 50000);

        Product p1 = new Product("iphone", 40000);
        Product p2 = new Product("charger", 5000);
        Product p3 = new Product("earbuds", 5000);

        ProductBundle pb = new ProductBundle("iphone bundle");
        pb.add(p1);
        pb.add(p2);
        pb.add(p3);

        Cart cart = new Cart();
        cart.addItem(pb);
        cart.addItem(p);

        System.out.println("Your Amazon Cart:");
        double total = 0;
        for (CartItem item : cart.cartItems) {
            item.display("-");
            total += item.getPrice();
        }

        System.out.println("\nTotal:" + total);
    }
}
