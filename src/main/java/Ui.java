import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        this.printLine();
        System.out.println("Hello! I'm Max");
        System.out.println("What can I do for you?");
        this.printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printAdded(Task task, int size) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void printDeleted(Task task, int size) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printLine();
    }

    public void printDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printLine();
    }

    public void printUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        tasks.printList();
        printLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file");
    }
}
