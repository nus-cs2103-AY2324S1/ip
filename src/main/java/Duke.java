import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Draws a line separating each conversation.
     *
     */
    public static void drawLine() {
        System.out.println("\t____________________________________________________________");
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public static void handleUserInput() {

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        System.out.println();
        String userInput;
        userInput = scanner.nextLine();
        String[] formattedUserInput = userInput.split(" ");
        String instruction  = formattedUserInput[0];
        drawLine();

        while (true) {
            if (instruction.equals("bye")) {
                break;
            }
            switch (instruction) {
            case "list":
                if (formattedUserInput.length > 1) {
                    System.out.println("\tInvalid Input. Try again.");
                } else {
                    taskList.listTasks();
                }
                break;
            case "mark":
                if (formattedUserInput.length != 2) {
                   System.out.println("\tInvalid Input. Try again.");
                } else {
                    try {
                        taskList.markTask(Integer.parseInt(formattedUserInput[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid input. Try again.");
                    }
                }
                break;
            case "unmark":
                if (formattedUserInput.length != 2) {
                    System.out.println("\tInvalid Input. Try again.");
                } else {
                    try {
                        taskList.unmarkTask(Integer.parseInt(formattedUserInput[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("\tInvalid Input. Try again.");
                    }
                }
                break;
            default:
                taskList.addTask(userInput);
                break;

            }
            drawLine();
            System.out.println();
            userInput = scanner.nextLine();
            formattedUserInput = userInput.split(" ");
            instruction = formattedUserInput[0];
            drawLine();
        }
    }

    public static void main(String[] args) {
        drawLine();
        System.out.println("\tHello I am Vishnu.");
        System.out.println("\tWhat can I do for you?");
        drawLine();
        handleUserInput();
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine();
    }
}
