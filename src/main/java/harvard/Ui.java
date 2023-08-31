package harvard;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public void displayWelcome() {
        displayLine();
        System.out.println("Hello! I'm Harvard\nWhat can I do for you?");
        displayLine();
    }
    public String readCommand() {
        return scanner.nextLine();
    }
    public void displayLine() {
        System.out.println("____________________________________________________________");
    }
    public void displayBye() {
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
    public void showAddTask(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }
    public void showList(TaskList tasks) {
        displayLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        displayLine();
    }
    public void showDelete(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Noted. I've removed this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }
    public void showDone(Task task) {
        displayLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        displayLine();
    }

    public void showUndone(Task task) {
        displayLine();
        System.out.println("Ok! I've marked this task as not done yet:");
        displayLine();
    }

    public void showFind(TaskList matchingTasks) {
        displayLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
        displayLine();
    }

    public void displayError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
