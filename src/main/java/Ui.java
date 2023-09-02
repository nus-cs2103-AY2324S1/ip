import java.util.Map;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String NAME = "Ip Bot";
    private Scanner scanner;
    private String currentCommand;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printDivider() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printWelcome() {
        printDivider();
        System.out.println("Hello I'm " + NAME + "!");
        System.out.println("While I may not be able to fight like Ip Man, I can assist you in other areas!");
        System.out.println("What can I do for you?");
        printDivider();
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public void readCommand() {
        this.currentCommand = scanner.nextLine().strip();
    }

    public Pair<String, Map<String, String>> processCommand() {
        return Parser.parseCommand(this.currentCommand);
    }

    public void printTaskListFormat(Task task, int number) {
        System.out.printf("%d. %s\n", number, task.toString());
    }

    public void printAddedItem(Task task, String taskTypeStr) {
        System.out.println("Added " + taskTypeStr + " item: " + task.toString());
    }

    public void printDeletedItem(Task task) {
        System.out.println("Deleted item: " + task.toString());
    }

    public void printMarkTask(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        System.out.println("Marking task as " + done + ": " + task.toString());
    }

    public void printAlreadyMarkedTask(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        System.out.println("Task was already marked as " + done + ": " + task.toString());
    }
}
