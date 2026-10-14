package Behavioral_Design_Pattern;

import java.util.*;

// The channel is the subject.
// The subscribers are the observers.
// The notification is the automatic update mechanism triggered by the subject.

// Observer Interface=======================================================
interface Subscriber{
    void update(String videoTitle);
}

// Concrete Observer:Email =================================================
class EmailSubscriber implements Subscriber{
    private String email;

    EmailSubscriber(String email){
        this.email = email;
    }

    @Override 
    public void update(String videoTitle){
        System.out.println("Email sent to " + email + ": New video uploaded - " + videoTitle);
    }
}

// Concrete Observer: Mobile App ============================================
class MobileAppSubscriber implements Subscriber{
    private String username;

    public MobileAppSubscriber(String username){
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println("In-app notification for " + username + ": New video - " + videoTitle);
    }
}

//Subject Interface
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

class YoutubeChannel implements  Channel{
    List<Subscriber> subscribers = new ArrayList<>();
    String channelName;

    public YoutubeChannel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        for(Subscriber sub: subscribers){
            sub.update(videoTitle);
        }
    }

    public void uploadVido(String videoTitle){
        System.out.println(videoTitle + " uploaded successfully..");
        notifySubscribers(videoTitle);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel("Striver");
        channel.subscribe(new EmailSubscriber("kritika@gmail.com"));
        channel.subscribe(new EmailSubscriber("Neena@gmail.com"));
        channel.subscribe(new MobileAppSubscriber("aditi012"));
        channel.subscribe(new MobileAppSubscriber("aditi014"));
        channel.uploadVido("Observer-pattern");
    }
}
