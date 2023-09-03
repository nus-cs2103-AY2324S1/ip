import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.print("Enter a command: ");
        return this.scanner.nextLine();
    }

    public static void showWelcome() {
        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
    }

    public void sayBye() {
        System.out.println("> Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    public static void showError(String message) {
        System.out.println("> OOPS!!! " + message);
    }

    public static void markResult(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("> Nice! I've marked this task as done:");
            System.out.println("\t" + task);
        } else {
            System.out.println("> OK, I've marked this task as not done yet:");
            System.out.println("\t" + task);
        }
    }

    public static void modifyListResult(Task task, TaskList taskList, boolean isAdd) {
        if (isAdd) {
            System.out.println("> Got it. I've added this task:");
        } else {
            System.out.println("> Noted. I've removed this task:");
        }
        System.out.println("\t" + task.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.getSize()));
    }

    public static void searchResult(LocalDate deadline, ArrayList<Task> resultList) {
        System.out.println("> Here are the tasks that are due on "
                + deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ": ");
        if (!resultList.isEmpty()) {
            for (Task t : resultList) {
                System.out.println("\t" + t);
            }
        } else {
            System.out.println("\tYay, you have no task due on this day!");
        }
    }

    public static void printList(TaskList taskList) {
        System.out.println("> Here are the tasks in your list:");

        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            System.out.println("\t" + (i + 1) + ". " + t.toString());
        }
    }
}
