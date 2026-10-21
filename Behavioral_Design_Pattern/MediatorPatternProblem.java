package Behavioral_Design_Pattern;

import java.util.*;

class User {
    private String username;
    private List<User> users;

    public User(String username) {
        this.username = username;
        this.users = new ArrayList<>();
    }

    public void addCollaborators(User user) {
        users.add(user);
    }

    public void makeChanges(String change) {
        for (User u : users) {
            u.broadcastChange(change, this);
        }
    }

    public void broadcastChange(String change, User user) {
        System.out.println("[ "+this.username+" ]"+change + " ==> changes made by " + user.username);
    }
}

public class MediatorPatternProblem {
    public static void main(String[] args) {
        User user = new User("Kritika");
        User user2 = new User("Kanika");
        User user3 = new User("Neena");
        User user4 = new User("Chander Shekhar");


        user.addCollaborators(user2);
        user.addCollaborators(user3);
        user.addCollaborators(user4);

        user.makeChanges(
            "changed H1 heading"
        );
    }
}
