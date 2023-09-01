import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final String DIVIDER = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(this.DIVIDER);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Chat Buddy!");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public void showExit() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("    " + errorMessage);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(ArrayList<String> taskStrings, String message) {
        System.out.println("    " + message);
        for (int i = 0; i < taskStrings.size(); i++) {
            String taskString = taskStrings.get(i);
            System.out.println(String.format("    %1s.%2s", i + 1, taskString));
        }
    }

    public void showTaskAddition(Task task, int totalNumOfTasks) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println(String.format("    Now you have %d tasks in the list.", totalNumOfTasks));
    }

    public void showTaskDeletion(Task task, int totalNumOfTasks) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println(String.format("    Now you have %d tasks in the list.", totalNumOfTasks));
    }

    public void showMarkTask(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task);
    }
}
