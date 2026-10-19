package Behavioral_Design_Pattern;

class Order {
    private String currentState;

    public Order() {
        this.currentState = "ORDER_PLACED";
    }

    public void next() {
        switch (currentState) {
            case "ORDER_PLACED":
                this.currentState = "PREPARING_ORDER";
                break;
            case "PREPARING_ORDER":
                this.currentState = "ORDER_DISPATCHED";
                break;
            case "ORDER_DISPATCHED":
                this.currentState = "ORDER_DELIVERED";
                break;
            default:
                break;
        }
    }

    public String cancelOrder() {
        if (currentState == "ORDER_PLACED" || currentState == "PREPARING_ORDER") {
            currentState = "ORDER_CANCELLED";
            return "Your Order is cancelled";
        }

        return "Your Order Can't be cancelled";
    }

    public void getOrderState(){
        System.out.println("Current State: " +this.currentState);
    }
}

public class StatePattern {
    public static void main(String[] args) {
        Order order = new Order();
        order.getOrderState();  // order_placed

        order.next();           
        order.getOrderState();  //preparing_order

        order.next();           
        order.getOrderState();  // order_dispatched

        System.out.println(order.cancelOrder());    //cant cancel

        order.next();           
        order.getOrderState();  //order delivered


    }
}
