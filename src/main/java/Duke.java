import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        greet();
        while (true) {
            String userInput = readMessage();
            if (!Objects.equals(userInput, "bye")) {
                sendMessage(userInput);
            } else {
                break;
            }
        }
        exit();
    }

    private static void greet() {
        sendMessage("Hello! I'm Heimdallr\nWhat can I do for you?");
    }

    private static void exit() {
        sendMessage(" Bye. Hope to see you again soon!");
    }

    public static void sendMessage(String msgs) {
        printLine();
        for (String msg: msgs.split("\n")) {
            System.out.println("\t" + msg);
        }
        printLine();
    }

    public static String readMessage() {
        return scanner.nextLine();
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}
