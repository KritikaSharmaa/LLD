package Structural_Design_Pattern;

interface Pizza {
    String description();

    double cost();
}

class CheesePizza implements Pizza {
    @Override
    public String description() {
        return "Cheese Pizza";
    }

    @Override
    public double cost() {
        return 100;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza inner;

    PizzaDecorator(Pizza inner) {
        this.inner = inner;
    }
}

class extraCheese extends PizzaDecorator {
    extraCheese(Pizza inner) {
        super(inner);
    }

    @Override
    public String description() {
        return inner.description() + " + extra cheese";
    }

    @Override
    public double cost() {
        return inner.cost() + 20;
    }
}

class Olives extends PizzaDecorator {
    Olives(Pizza inner) {
        super(inner);
    }

    @Override
    public String description() {
        return inner.description() + " + Olives";
    }

    @Override
    public double cost() {
        return inner.cost() + 10;
    }

}

public class DecoratorPattern {
    public static void main(String[] args) {
        Pizza pizza = new Olives(new extraCheese(new CheesePizza()));
        System.out.println("Description: "+pizza.description());
        System.out.println("Cost: "+pizza.cost());
    }
}
