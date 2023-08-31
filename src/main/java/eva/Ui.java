package eva;

import eva.task.Task;
import eva.task.TaskList;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public static void showWelcome() {
        String logo = "  ______          \n"
                + " |  ____|         \n"
                + " | |____   ____ _ \n"
                + " |  __\\ \\ / / _` |\n"
                + " | |___\\ V / (_| |\n"
                + " |______\\_/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Eva.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    public static void showExit() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        System.out.println("No saved tasks found.");
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void showAdded(Task task, int size) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t\t" + task);
        System.out.println("\t Now you have " + size + " task(s) in the list.");
    }

    public static void showDeleted(Task task, int size) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + task);
        System.out.println("\t Now you have " + size + " tasks in the list.");
    }
    public static void showMarked(Task task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + task);
    }

    public static void showUnmarked(Task task) {
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + task);
    }
    public static void showList(TaskList tasks) {
        tasks.listTasks();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            return input;
        }
    }
}
