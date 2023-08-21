import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String CHAT_BOT_NAME = "Genos";
    private ArrayList<String> list;

    public Duke() {
        this.list = new ArrayList<String>();
    }

    private static void greet() {
        System.out.println("Hello I'm " + Duke.CHAT_BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("Please type your command below, I will store what you said");
        System.out.println("Usage: \"list\" to see the list of text stored, \"bye\" to exit");
    }

    private void converse() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (! command.equals("bye")) {
            if (command.equals("list")) {
                this.listCommands();
            } else {
                System.out.println("    Added: " + command);
                this.list.add(command);
            }
            command = sc.nextLine();
        }
        System.out.println("    Goodbye, Hope to see you again soon.");
    }

    private void listCommands() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.list.get(i));
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.converse();
    }
}
