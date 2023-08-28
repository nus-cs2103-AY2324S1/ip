package ui;

import java.util.Scanner;

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

    private void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(DIVIDER + LOGO + "\n" + welcomeMessage + DIVIDER);
    }

    private void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage);
        printDivider();
    }

    private void printListMessage(TaskList taskList) {
        int numOfTasks = taskList.size();
        if (numOfTasks == 0) {
            System.out.println("You currently have no tasks! Good Job!");
        } else {
            if (numOfTasks == 1) {
                System.out.printf("\nNow you have %d task in your list!%n", numOfTasks);
            } else {
                System.out.printf("\nNow you have %d tasks in your list!%n", numOfTasks);
            }
            printList(taskList);
        }
        printDivider();
    }

    //TODO
    private void printError(Exception e) {

    }

    private void printList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            //System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
        }
    }
}
