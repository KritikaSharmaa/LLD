package Creational_Design_Pattern;

//Factory design pattern is used when conditional logic is used to create an object. It provides a way to create objects without exposing the instantiation logic to the client and refers to the newly created object using a common interface.
//i.e. Rather than calling a constructor directly to create an object, the client calls a factory method. 
//The factory method then creates the object and returns it to the client. This allows the client to create objects without knowing the exact class of the object that will be created. we create a factory class that has a method which returns different types of objects based on the input provided to it. This is useful when we have a superclass with multiple subclasses and based on input, we need to return one of the subclass. This pattern takes out the responsibility of instantiating a class from the client code and places it in a factory class.

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}

public class FactoryPattern {
    public static void main(String[] args){
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape obj = shapeFactory.getShape("CIRCLE");
        System.out.println("Object created is of type: " + obj.getClass().getSimpleName());
        obj.draw();
        shapeFactory.getShape("SQUARE").draw();
    }
}
