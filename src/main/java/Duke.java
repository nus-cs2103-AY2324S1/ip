import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        printWelcome();

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                printList();
            } else {
                addToList(command);
            }
        }

        printExit();
    }

    private static void printMessage(String message) {
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println("\n" + line + "\n");
    }

    private static void printMessage(String[] message) {
        System.out.println(line);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + line + "\n");
    }

    private static void printWelcome() {
        String[] message = {
                "Hello! I'm " + name,
                "What can I do for you?"
        };

        printMessage(message);
    }

    private static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private void addToList(String text) {
        list.add(text);
        printMessage("added: " + text);
    }

    private void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, list.get(i));
        }

        System.out.println("\n" + line + "\n");
    }

}
