import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static String partition = "--------------------------------------";

    public Ui() {
        sc = new Scanner(System.in);

        System.out.println(partition + "\n" + "Hello! I'm Rion");
        System.out.println("What can I do for you?\n" + partition);
    }

    void addToListSuccess(Task task, int size) {
        System.out.println(partition + "\nadded:\n" + task + "\n" +
        "You have " + size + " tasks in the list.\n" + partition);
    }

    void deleteFromListSuccess(Task task, int size) {
        System.out.println(partition + "\nOK, I've deleted the task:\n" 
        + task + "\nNow you have " + size + " tasks in the list.");
    }

    void printList(ArrayList<Task> taskList) {
        System.out.println(partition + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + taskList.get(i));
        }
        System.out.println(partition);
    }

    void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    void printError(String message) {
        printMessage(message);
    }

    void showLoadingError(String error) {
        printMessage("OOPS! An error occurred during file loading " + error);
    }

    void printMessage(String message) {
        System.out.println(partition + "\n" + message + "\n" + partition);
    }

    String nextCommand() {
        return sc.nextLine();
    }
}
