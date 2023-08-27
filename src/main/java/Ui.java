import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println("☹ " + errorMessage);
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTaskAddedMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showTaskMarkedAsDone(TaskList tasks, int taskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    public void showTaskMarkedAsUndone(TaskList tasks, int taskIndex) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex));
    }

    public void showTaskRemoved(TaskList tasks, Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }
}
