package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // Constants
    private static final String NAME = "Beep Boop Bot";

    // Fields
    private Scanner sc;

    // Constructor
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // Methods
    public void printLine() {
        System.out.println("─".repeat(100));
    }
    public void printMessage(String message) {
        System.out.printf("\t%s\n", message);
        printLine();
    }

    public void printGreetingMessage() {
        printLine();
        String greetingMessage = String.format("Hello! I'm %s!\n\tHow can I help you?\n", NAME);
        printMessage(greetingMessage);
    }

    public void printExitMessage() {
        String exitMessage = "Bye Bye! Hope to see you again soon! Beep Boop!";
        printMessage(exitMessage);
    }

    public void printList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, list.get(i));
        }
        printLine();
    }

    public void printAddSuccessMessage(Task task, ArrayList<Task> list) {
        System.out.println("\tGot it. I've added this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n", task.toString(), list.size());
        printLine();
    }

    public void printDeleteSuccessMessage(int index, ArrayList<Task> list) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.printf("\t\t%s\n\tNow you have %d tasks in the list.\n",
                list.get(index).toString(), list.size() - 1);
        printLine();
    }

    public void showLoadingError() {
        printMessage("Boop Beep OOPS! I seem to have troubles loading the file :(");
    }

    public void showError(String error) {
        printMessage(error);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
