interface UberRide{
    public void bookRide();
    public void acceptRide();
    public void startRide();
    public void endRide();
    public void rateDriver();
}

// This class violates the Interface Segregation Principle (ISP) because it implements methods that are not applicable to its role.
class Rider implements UberRide{
    @Override
    public void bookRide() {
        System.out.println("Rider booked a ride.");
    }

    @Override
    public void acceptRide() {
        // This method is not applicable for Rider, but we have to implement it because of the interface.
        throw new UnsupportedOperationException("Riders cannot accept rides.");
    }

    @Override
    public void startRide() {
        // This method is not applicable for Rider, but we have to implement it because of the interface.
        throw new UnsupportedOperationException("Riders cannot start rides.");
    }

    @Override
    public void endRide() {
        // This method is not applicable for Rider, but we have to implement it because of the interface.
        throw new UnsupportedOperationException("Riders cannot end rides.");
    }

    @Override
    public void rateDriver() {
        System.out.println("Rider rated the driver.");
    }
}


public class ISP_Bad {
    public static void main(String[] args){

    }
}
