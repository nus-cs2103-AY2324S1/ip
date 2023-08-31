import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String getUserCommand() {
        return scanner.nextLine();
    }
    public void printDivider() {
        System.out.println('\t' + "_________________________________________");
    }
    public void printGreeting(String name) {
        printDivider();
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
        printDivider();
    }
    public void printTaskAddedMessage(Task task, TaskList tasks) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println(String.format("\t\t %s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.getSize()));
    }
    public void printTaskDeletedMessage(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
    }
    public void printTaskMarkedMessage(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }
    public void printTaskUnmarkedMessage(Task task) {
        System.out.println("\tOk, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }

    public void showLoadingError() {
        System.out.println("Loading error...");
    }

    public void showError(String message) {
        System.out.println(message);
    }
    public void printNotSureMessage() {
        System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void printTask(int id, Task task) {
        System.out.println(String.format("\t%d.%s", id + 1, task.toString()));
    }

    public void bidFarewell() {
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
