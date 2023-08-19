import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greet();
        while (true) {
            String userInput = readMessage();
            if (userInput.equals("list")) {
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < taskCount; i++) {
                    output.append(i + 1).append(". ").append(tasks[i]).append("\n");
                }
                sendMessage(output.toString());
            } else if (userInput.contains("unmark")) {
                int choice = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (choice < 1 || choice > taskCount) {
                    sendMessage("Invalid Choice Provided!");
                    continue;
                }
                tasks[choice].markAsNotDone();
                sendMessage("OK, I've marked this task as not done yet:\n  " + tasks[choice]);
            } else if (userInput.contains("mark")) {
                int choice = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (choice < 1 || choice > taskCount) {
                    sendMessage("Invalid Choice Provided!");
                    continue;
                }
                tasks[choice].markAsDone();
                sendMessage("Nice! I've marked this task as done:\n  " + tasks[choice]);
            } else if (!Objects.equals(userInput, "bye")) {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                sendMessage("added: " + userInput);
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
