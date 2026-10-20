package Behavioral_Design_Pattern;


abstract class CustomerServiceHandler {
    protected CustomerServiceHandler nextHandler;

    public void setNextHandler(CustomerServiceHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    abstract public void handleRequest(String requestType);
}

class GeneralSupport extends CustomerServiceHandler {

    @Override
    public void handleRequest(String requestType) {
        if (requestType == "general") {
            System.out.println("Call is being forward to General Support Team");
        }else{
            nextHandler.handleRequest(requestType);
        }
    }
}

class TechnicalSupport extends CustomerServiceHandler {

    @Override
    public void handleRequest(String requestType) {
        if (requestType == "technical") {
            System.out.println("Call is being forward to Technical Support Team");
        }else{
            nextHandler.handleRequest(requestType);
        }
    }
}

class BillingSupport extends CustomerServiceHandler {

    @Override
    public void handleRequest(String requestType) {
        if (requestType == "billing") {
            System.out.println("Call is being forward to Billing Support Team");
        }else{
            nextHandler.handleRequest(requestType);
        }
    }
}


public class ChainOfResponsibility {
    public static void main(String[] args) {
        CustomerServiceHandler general = new GeneralSupport();
        CustomerServiceHandler technical = new TechnicalSupport();
        CustomerServiceHandler billing = new BillingSupport();

        // Setting up the chain: general -> technical -> billing
        general.setNextHandler(technical);
        technical.setNextHandler(billing);

        // Testing the chain of responsibility with different request types
        general.handleRequest("technical");

    }
}