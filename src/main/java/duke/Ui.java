package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
    public void showWelcome() {
        System.out.println(
                "____________________________________________________________\n"
                        + "Hello! I'm ET\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n"
        );
    }

    public void showExit() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void showMarked(Task t) {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + t + "\n"
                + "____________________________________________________________\n"
        );
    }

    public void showUnmarked(Task t) {
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + t + "\n"
                + "____________________________________________________________\n"
        );
    }

    public void showTaskAdded(Task task, int num) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + num + " tasks in the list.\n"
                + "____________________________________________________________\n"
        );
    }

    public void showDeleted(Task t, int num) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + t + "\n"
                + "Now you have " + num + " tasks in the list.\n"
                + "____________________________________________________________\n"
        );
    }

    public void showTaskList() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
    }

    public void showTask(Task t, int index) {
        System.out.println(index + ". " + t.toString());
    }

    public void showMatchingTasks(TaskList tasks) {
        System.out.println("____________________________________________________________\n"
                + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.total(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }

    public void showError(String errorMsg) {
        System.out.println("____________________________________________________________\n"
                + errorMsg + "\n"
                + "____________________________________________________________\n"
        );
    }

}
