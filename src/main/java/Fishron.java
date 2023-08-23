import java.util.Scanner;

public class Fishron {
    private String name;

    public Fishron(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String botName = "Fishron";
        Fishron chatBot = new Fishron(botName);

        chatBot.greet();
        System.out.println("____________________________________________________________");

        chatBot.farewell();
        System.out.println("____________________________________________________________");
    }
}