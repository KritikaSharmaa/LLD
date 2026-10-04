package Creational_Design_Pattern;

interface mainCourse {
    void prepare();
}

class VegMainCourse implements mainCourse {
    @Override
    public void prepare() {
        System.out.println("Preparing Veg Main Course");
    }
}

class NonVegMainCourse implements mainCourse {
    @Override
    public void prepare() {
        System.out.println("Preparing Non-Veg Main Course");
    }
}

interface Dessert {
    void prepare();
}

class VegDessert implements Dessert {
    @Override
    public void prepare() {
        System.out.println("Preparing Veg Dessert");
    }
}

class NonVegDessert implements Dessert {
    @Override
    public void prepare() {
        System.out.println("Preparing Non-Veg Dessert");
    }
}

//Abstract Factory interface that defines methods for creating main course and dessert objects.
interface MealFactory {
    mainCourse createMainCourse();
    Dessert createDessert();
}

class VegMealFactory implements MealFactory {
    @Override
    public mainCourse createMainCourse() {
        return new VegMainCourse();
    }

    @Override
    public Dessert createDessert() {
        return new VegDessert();
    }
}

class NonVegMealFactory implements MealFactory {
    @Override
    public mainCourse createMainCourse() {
        return new NonVegMainCourse();
    }

    @Override
    public Dessert createDessert() {
        return new NonVegDessert();
    }
}


public class AbstractFactoryPattern {
    public static void main(String[] args) {
        // MealFactory vegMealFactory = new VegMealFactory();
        // vegMealFactory.createMainCourse().prepare();
        // vegMealFactory.createDessert().prepare();

        MealFactory nonVegMealFactory = new NonVegMealFactory();
        nonVegMealFactory.createMainCourse().prepare();
        nonVegMealFactory.createDessert().prepare();
    }
}