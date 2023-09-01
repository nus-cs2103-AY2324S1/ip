package Remy;

import Remy.task.Task;
import Remy.task.TaskList;

import java.util.Scanner;

public class Ui {

    public final Scanner scanner;
    private static String divider = "____________________________________________________________\n";
    private static String shortDivider = "_____________\n";
    private static String welcomeMessage =
            "I'm Remy.Remy, and it is NOT nice to see you.\n" +
                    "Faster tell me what you want and go away.";

    private static String exitMessage = "Hope to never see you again!\n" + divider;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void printDivider() {
        System.out.println(divider);
    }

    public static void printWelcomeMessage() {
        printLongSandwich(welcomeMessage);
    }

    public static void printExitMessage() {
        System.out.println(exitMessage);
        System.out.println(divider);
    }

    public static void printShortSandwich(String content) {
            System.out.println(shortDivider);
            System.out.println(content);
            System.out.println(shortDivider);
    }

    public static void printLongSandwich(String content) {
        System.out.println(divider);
        System.out.println(content);
        System.out.println(divider);
    }

    public static void printError(String errorMessage) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(errorMessage);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public static void printList(TaskList taskList) {
        printShortSandwich(taskList.toString());
    }

    public static void printAddedTask(Task task, int num) {
        String content = "Added, now scram.\n" +
                task.toString() + "\n" +
                "Now you have " + num + " tasks in the list.";
        Ui.printShortSandwich(content);
    }

    //@@author samuelim01-reused
    // Reused Samuel's inplementation of this method
    public String readCommand() {
        String input = scanner.nextLine();
        while (input.trim().isEmpty()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
