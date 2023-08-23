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

    public void echoCommand(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String botName = "Fishron";
        Fishron chatBot = new Fishron(botName);

        chatBot.greet();
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            chatBot.echoCommand(input);
        } while (!input.equalsIgnoreCase("bye"));

        chatBot.farewell();
        System.out.println("____________________________________________________________");
    }
}