package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void showGreeting() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm yourChatBot");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public static void showList(TaskList taskList) {
        String todolistoutput = "";
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            todolistoutput += i + 1 + ". " + taskList.getTasks().get(i) + "\n";
        }
        System.out.println(LINE_SEPARATOR);
        System.out.println(todolistoutput);
        System.out.println(LINE_SEPARATOR);
    }

    public static void showMarkedAsDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println(LINE_SEPARATOR);
    }

    public static void showUnmarked(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        System.out.println(LINE_SEPARATOR);
    }

    public static void showRemoved(Task task, int totalTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void showError(String errorMessage) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(errorMessage);
        System.out.println(LINE_SEPARATOR);
    }

    public static void showAddConfirmation(Task task, int totalTasks) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void showByeMessage() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }
}
