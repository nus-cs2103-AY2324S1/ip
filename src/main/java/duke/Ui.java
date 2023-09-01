package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm duke.");
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

    public void showMatchingTasks(TaskList matchingTasks) {
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + matchingTasks.get(i));
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
