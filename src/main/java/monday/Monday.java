package monday;

import java.util.Scanner;

import monday.monday.exception.MondayExceptions;
import monday.monday.parser.Parser;
import monday.monday.ui.Ui;
import monday.task.TaskList;

/**
 * Monday is a task management ChatBot. Users can add, mark, unmark,
 * delete, keep track of the tasks they have.
 */
public class Monday {

    private final TaskList taskList = new TaskList("./data/duke.txt");

    /**
     * Starts the Monday application.
     * Initializes necessary components, greets the user, handles user input, and handles errors.
     */
    private void startMonday() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        Ui.printSeparator();
        System.out.println(Ui.greet());
        Ui.printSeparator();
        while (isRunning) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                isRunning = false;
            }
            Ui.printSeparator();
            try {
                System.out.println(Parser.mondayParser(userInput, taskList));
            } catch (MondayExceptions e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Mark/UnMark number error. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of Bound Error: " + e.getMessage());
            }
            Ui.printSeparator();
        }
        scanner.close();
    }

    public String getResponse(String input) {
        try {
            return Parser.mondayParser(input, taskList);
        } catch (MondayExceptions e) {
            return e.toString();
        }
    }

    /**
     * Entry point to Monday.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.startMonday();
    }
}
