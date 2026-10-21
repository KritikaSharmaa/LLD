package Behavioral_Design_Pattern;

import java.util.*;

interface Mediator {
    public void broadcastChange(String change, User sender);

    public void join(User user);
}

class CollaborativeDocument implements Mediator {
    List<User> collaborators = new ArrayList<>();

    public void broadcastChange(String change, User sender) {
        for (User user : collaborators) {
            if (user != sender) {
                user.receiveChange(change, sender);
            }
        }
    }

    public void join(User user) {
        collaborators.add(user);
    }
}

class User {
    private String name;
    private CollaborativeDocument collaborativeDocument;

    public User(String name, CollaborativeDocument mediator) {
        this.name = name;
        collaborativeDocument = mediator;
    }

    public void makeChanges(String change) {
        collaborativeDocument.broadcastChange(change, this);
    }

    // Method to receive a change from another user
    public void receiveChange(String change, User sender) {
        System.out.println(name + " saw change from " + sender.name + ": \"" + change + "\"");
    }
}

public class MediatorPatternSolution {
    public static void main(String[] args) {
        CollaborativeDocument cd = new CollaborativeDocument();

        User user1 = new User("Kritika", cd);
        User user2 = new User("Kanika", cd);
        User user3 = new User("Neena", cd);
        User user4 = new User("Chander Shekhar", cd);

        cd.join(user1);
        cd.join(user2);
        cd.join(user3);
        cd.join(user4);

        user1.makeChanges("changed name of the document");
    }
}
