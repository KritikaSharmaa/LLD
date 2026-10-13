package Structural_Design_Pattern;

interface Device{
    void turnON();
    void turnOFF();
}

class TV implements Device{
    @Override
    public void turnON() {
        System.out.println("TV turned ON");
    }

    @Override
    public void turnOFF() {
        System.out.println("TV turned OFF");
    }
}

class Radio implements Device{
    @Override
    public void turnON() {
        System.out.println("Radio turned ON");
    }

    @Override
    public void turnOFF() {
        System.out.println("Radio turned OFF");
    }
}

//agr interface use krungi abstract k jagah mujhe har ek remote k liye play and stop likhna pdega
abstract class Remote{
    Device device;

    Remote(Device device){
        this.device = device;
    }

    public void play(){
        device.turnON();
    }

    public void stop(){
        device.turnOFF();
    }
}

class SimpleRemote extends Remote{
    SimpleRemote(Device device) {
        super(device);
    }
}

class AdvanceRemote extends Remote{
    AdvanceRemote(Device device){
        super(device);
    }
}

public class BridgePattern {
    public static void main(String[] args){
        Remote remote = new AdvanceRemote(new TV());
        remote.play();
        remote.stop();

        remote = new SimpleRemote(new Radio());
        remote.play();
        remote.stop();
    }
}
