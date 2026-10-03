package Creational_Design_Pattern;

import java.util.List;

class Pizza {
    // Mandatory fields
    private String dough;
    private String sauce;

    // Optional fields
    private String topping;
    private String cheese;
    private List<String> sides;

    public Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
        this.cheese = builder.cheese;
        this.sides = builder.sides;
    }

    public static class Builder {
        // Mandatory fields
        private String dough;
        private String sauce;

        // Optional fields
        private String topping;
        private String cheese;
        private List<String> sides;

        public Builder(String dough, String sauce) {
            this.dough = dough;
            this.sauce = sauce;
        }

        public Builder setTopping(String topping) {
            this.topping = topping;
            return this;
        }

        public Builder setCheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder setSides(List<String> sides) {
            this.sides = sides;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public String toString() {
        return "Pizza--->[" +
                "dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", topping='" + topping + '\'' +
                ", cheese='" + cheese + '\'' +
                ", sides=" + sides +
                ']';
    }

}

public class BuilderPattern_Solution {
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder("Thin Crust", "Tomato Basil")
                .setTopping("Pepperoni")
                .setCheese("Mozzarella")
                .setSides(List.of("Garlic Bread", "Coke"))
                .build();
        System.out.println(pizza.toString());
    }
}
