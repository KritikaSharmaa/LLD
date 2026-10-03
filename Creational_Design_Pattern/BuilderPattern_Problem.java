package Creational_Design_Pattern;

import java.util.List;

class Pizza{
    //Mandatory fields
    private String dough;
    private String sauce;

    //Optional fields
    private String topping;
    private String cheese;
    private List<String> sides;

    //Constructor to initialize the mandatory and optional fields of the Pizza class. It takes in the values for dough, sauce, topping, cheese, and sides as parameters and assigns them to the corresponding instance variables.
    //this is a bad way to create an object of Pizza class as it has too many parameters and it is not clear which parameter is for which field. It is also not clear which parameters are mandatory and which are optional
    //When we have a class with many parameters, it is better to use the builder pattern to create an object of that class. The builder pattern allows us to create an object step by step and it is clear which parameter is for which field. It also allows us to create an object with only the mandatory fields and set the optional fields later.
    public Pizza(String dough, String sauce, String topping, String cheese, List<String> sides) {
        this.dough = dough;
        this.sauce = sauce;
        this.topping = topping;
        this.cheese = cheese;
        this.sides = sides;
    }

    //toString method is predefined in Object class and is used to return a string representation of an object. It is overridden here to provide a custom string representation of the Pizza object.
    public String toString() {
        return "Pizza{" +
                "dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", topping='" + topping + '\'' +
                ", cheese='" + cheese + '\'' +
                ", sides=" + sides +
                '}';
    }

}

public class BuilderPattern_Problem {
    public static void main(String[] args){
        Pizza pizza = new Pizza("Thin Crust", "Tomato Basil", "Pepperoni", "Mozzarella", List.of("Garlic Bread", "Coke"));
        System.out.println(pizza.toString());
    }
}
