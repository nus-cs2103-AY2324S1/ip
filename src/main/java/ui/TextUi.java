package ui;

import java.util.Scanner;

import exception.BobInvalidTaskNumberException;
import task.Task;
import task.TaskList;
public class TextUi {
    private static final String DIVIDER = "\n____________________________________________________________\n";
    private static final String LOGO =
            ".-. .-')              .-. .-')   \n" +
                    "\\  ( OO )             \\  ( OO )  \n" +
                    " ;-----.\\  .-'),-----. ;-----.\\  \n" +
                    " | .-.  | ( OO'  .-.  '| .-.  |  \n" +
                    " | '-' /_)/   |  | |  || '-' /_) \n" +
                    " | .-. `. \\_) |  |\\|  || .-. `.  \n" +
                    " | |  \\  |  \\ |  | |  || |  \\  | \n" +
                    " | '--'  /   `'  '-'  '| '--'  / \n" +
                    " `------'      `-----' `------'  ";

    private Scanner scanner;

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(DIVIDER + LOGO + "\n" + welcomeMessage + DIVIDER);
    }

    public void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage);
        printDivider();
    }

    public void printListEndMessage(TaskList taskList) {
        int numOfTasks = taskList.size();
        if (numOfTasks == 0) {
            System.out.println("You currently have no tasks! Good Job!");
        } else {
            if (numOfTasks == 1) {
                System.out.printf("\nNow you have %d task in your list!%n", numOfTasks);
            } else {
                System.out.printf("\nNow you have %d tasks in your list!%n", numOfTasks);
            }
        }
        printDivider();
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
        printDivider();
    }

    public void printListMessage(TaskList taskList) throws BobInvalidTaskNumberException {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
        }
        printListEndMessage(taskList);
    }

    public void printMarkMessage(Task task, boolean isDone) {
        System.out.println();
        if (isDone) {
            System.out.println("Great Job! I've helped mark this task as done:\n" + task.toString());
        } else {
            System.out.println("No worries! I will help you unmark this task:\n" + task.toString());
        }
        printDivider();
    }

    public void printDeleteMessage(Task task) {
        System.out.println("Foosh! Let it be gone! I've helped delete the task:\n" +
                task.toString());
        printDivider();
    }

    public void printAddMessage(Task task) {
        String displayMessage = "I gotchu. New task added to the list:\n";
        System.out.println(displayMessage + task.toString());
        printDivider();
    }

    public String readTextInput() {
        return scanner.nextLine();
    }
}
