package Creational_Design_Pattern;

interface emailTemplate extends Cloneable {
    emailTemplate clone();

    void setContent(String content);

    void send(String sendTo);
}

class welcomeEmail implements emailTemplate {
    private String content;
    private String sendTo;

    public welcomeEmail(String content, String sendTo) {
        this.content = content;
        this.sendTo = sendTo;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public welcomeEmail clone() {
        try {
            return (welcomeEmail) super.clone();
        } catch (Exception e) {
            System.out.println("Cloned Failed " + e);
            throw new RuntimeException("Cloned Failed");
        }
    }

    public String toString() {
        return "welcomeEmail{content='" + content + "', sendTo='" + sendTo + "'}";
    }

}

public class PrototypePattern {
    public static void main(String[] args) {
        welcomeEmail emailtemp1 = new welcomeEmail("Hi, welcome 1", "kritika@gmail.com");
        welcomeEmail emailtemp2 = emailtemp1.clone();
        System.out.println(emailtemp1.toString());
        System.out.println(emailtemp2.toString());

        emailtemp2.setContent("Hi Kanika, welcome to TUF+");
        emailtemp2.send("kanika");

        System.out.println(emailtemp1.toString());
        System.out.println(emailtemp2.toString());
    }
}
