import java.util.Scanner;

public class Duke {
    private static String CHAT_BOT_NAME = "Genos";

    private static void greet() {
        System.out.println("Hello I'm " + Duke.CHAT_BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println("(Please type your command below, I will repeat what you said)");
    }

    private static void converse() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (! command.equals("bye")) {
            System.out.println("    I heard you said: " + command);
            command = sc.nextLine();
        }
        System.out.println("    Goodbye, Hope to see you again soon.");
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.converse();
    }
}
