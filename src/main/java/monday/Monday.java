package monday;

import java.util.Scanner;

import monday.monday.exception.MondayException;
import monday.monday.parser.Parser;
import monday.monday.ui.Ui;
import monday.task.TaskList;

/**
 * Monday is a task management ChatBot. Users can add, mark, unmark,
 * delete, keep track of the tasks they have.
 */
public class Monday {

    private final TaskList taskList = new TaskList("./data/duke.txt");

    private final Parser p = new Parser(taskList);

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
                System.out.println(p.parseCommands(userInput));
            } catch (MondayException e) {
                Ui.printErrorMessage("", e);
            } catch (NumberFormatException e) {
                Ui.printErrorMessage("Mark/UnMark number error: ", e);
            } catch (IllegalArgumentException e) {
                Ui.printErrorMessage("Argument Error: ", e);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMessage("Index out of Bound Error: ", e);
            }
            Ui.printSeparator();
        }
        scanner.close();
    }

    /**
     * Gets response based on user input.
     *
     * @param input User input to process
     * @return Response based on user input.
     */
    public String getResponse(String input) {
        try {
            return p.parseCommands(input);
        } catch (MondayException e) {
            return e.toString();
        }
    }

    /**
     * The entry point to the chatbot.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Monday monday = new Monday();
        monday.startMonday();
    }
}
