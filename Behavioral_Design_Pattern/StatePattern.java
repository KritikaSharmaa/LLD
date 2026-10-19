package Behavioral_Design_Pattern;

// OrderContext class manages the current state of the order
class OrderContext {
    private OrderState currentState;

    public OrderContext() {
        this.currentState = new OrderPlacedState();
    }

    // Constructor initializes the state to ORDER_PLACED
    public void setState(OrderState state) {
        currentState = state;
    }

    // Method to move the order to the next state
    public void next() {
        currentState.next(this);
    }

    // Method to cancel the order
    public void cancelOrder() {
        currentState.cancelled(this);
    }

    public String getState() {
        return currentState.getStateName();
    }

}

interface OrderState {
    void next(OrderContext context);

    void cancelled(OrderContext context);

    String getStateName();
}

class OrderPlacedState implements OrderState {

    @Override
    public void next(OrderContext context) {
        context.setState(new PrepareOrderState());
        System.out.println("Order is now being prepared.");
    }

    @Override
    public void cancelled(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStateName() {
        return "ORDER_PLACED";
    }

}

class PrepareOrderState implements OrderState {

    @Override
    public void next(OrderContext context) {
        context.setState(new DeliverOrderState());
        System.out.println("Order is now being dispatched.");
    }

    @Override
    public void cancelled(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStateName() {
        return "ORDER_DISPATCHED";
    }

}

class DeliverOrderState implements OrderState {

    @Override
    public void next(OrderContext context) {
        context.setState(null);
        System.out.println("Order is delivered.");
    }

    @Override
    public void cancelled(OrderContext context) {
        System.out.println("Order cant be cancelled.");
    }

    @Override
    public String getStateName() {
        return "ORDER_DELIVERED";
    }

}

// CancelledState handles the behavior when the order is cancelled
class CancelledState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("Cancelled order cannot move to next state.");
    }

    public void cancelled(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    public String getStateName() {
        return "CANCELLED";
    }
}

public class StatePattern {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();
        System.out.println(order.getState());   //order placed

        order.next();   //preparing order
        order.cancelOrder(); // cant cancel
        order.next();   //order dispatched
        order.next(); //order delivered
        System.out.println(order.getState()); //order delivered

    }
}
