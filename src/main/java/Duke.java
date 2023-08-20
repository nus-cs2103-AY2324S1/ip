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
    private static List<Task> tasks = new ArrayList<>();

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
        } else if (command.startsWith("mark")) {
            markTask(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            addTask(command);
        } else {
            printErrorMessage();
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

        Task newTask = null;

        if (task.startsWith("todo")) {
            newTask = ToDo.createToDoFromCommand(task);
        } else if (task.startsWith("deadline")) {
            newTask = Deadline.createDeadlineFromCommand(task);
        } else if (task.startsWith("event")) {
            newTask = Event.createEventFromCommand(task);
        }

        tasks.add(newTask);
        printAddedTaskConfirmation(newTask);
    }

    private static void markTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
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

    private static void printAddedTaskConfirmation(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void printErrorMessage() {
        System.out.println(LINE);
        System.out.println("I'm sorry, but I don't know what that means");
        System.out.println(LINE);
    }

}
