package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    String logo = " ___  __    ________  ________  ________   ________  ________      \r\n" + //
            "|\\  \\|\\  \\ |\\   __  \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\     \r\n" + //
            "\\ \\  \\/  /|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \r\n" + //
            " \\ \\   ___  \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \r\n" + //
            "  \\ \\  \\\\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \r\n" + //
            "   \\ \\__\\\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \r\n" + //
            "    \\|__| \\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\_________\\\r\n" + //
            "                                                       \\|_________|";

    String divider = "\n____________________________________________________________\n";

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printDivider() {
        System.out.println(divider);
    }

    public void invalidCommandMessage() {
        printDivider();
        showError("Do not test my patience, mortal. Speak clearly.");
        printDivider();
    }

    public void startMessage() {
        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat foolish errand do you seek to accomplish with my immense powers?");
        printDivider();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String commandPrompt() {
        return sc.nextLine();
    }

    public void listTasks(TaskList taskList, boolean searching) {
        printDivider();
        if (searching) {
            System.out.println("The following tasks match what you seek:\n");
        } else {
            System.out.println("You have somehow found the audacity to conjure up this laughable list of inconsequential endeavours:\n");
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.getTask(i - 1));
        }
        printDivider();
    }

    public void markTaskMessage(Task task) {
        printDivider();
        System.out.println(
                "Astonishingly enough, you have managed to triumph over this mind-bogglingly simple task:\n");
        System.out.println(task.toString());
        printDivider();
    }

    public void unmarkTaskMessage(Task task) {
        printDivider();
        System.out.println(
                "You have somehow managed to fail this mind-bogglingly simple task:\n");
        System.out.println(task.toString());
        printDivider();
    }

    public void todoMessage(Task task) {
        printDivider();
        System.out.println("This task has been reluctantly bestowed upon your ever-growing list:\n");
        System.out.println(task.toString());
    }

    public void deadlineMessage(Task task) {
        printDivider();
        System.out.println(
                "With your constant mediocrity, it is entirely unlikely that you will be able to meet this deadline I have just added: \n");
        System.out.println(task.toString());
    }

    public void eventMessage(Task task) {
        printDivider();
        System.out.println(
                "Looks like I will have to slow time down myself if you wish to make it to this event I just added:\n");
        System.out.println(task.toString());
    }

    public void deleteMessage(Task task) {
        printDivider();
        System.out.println(
                "One less annoyance to plague your feeble list. This task has been banished:\n");
        System.out.println(task.toString());
    }

    public void endMessage() {
        printDivider();
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        printDivider();
    }

    public void taskListSizeMessage(int size, boolean growing) {
        if (growing) {
            System.out.println("Congratulations, your pile of tasks has swelled to a whopping " + size + ".");
        } else {
            System.out.println("Your pile of tasks has shrunk to a measly " + size + ".");
        }
        printDivider();
    }
}
