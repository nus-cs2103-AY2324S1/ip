import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LOGO = ",------.,--.              ,--.  \n"
                                + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
                                + "|  `--, |     /|  ||  |' .-. |   \n"
                                + "|  `---.|  \\\\  \\\\  ''  '\\\\ `-\'   \n"
                                + "`------'`--'`--'`----'  `---' \n";
    private static final String LINE = "-".repeat(60);
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        handleUserInput();
        printFarewellMessage();
    }

    private static void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                sc.close();
                break;
            }
            handleCommand(input);
        }
    }

    private static void handleCommand(String command) {
        if (command.equals("list")) {
            printList();
        } else {
            addTask(command);
        }
    }

    private static void printList() {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static void addTask(String task) {
        tasks.add(task);
        System.out.println(LINE);
        System.out.println("added: " + task);
        System.out.println(LINE);
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm \\n");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
