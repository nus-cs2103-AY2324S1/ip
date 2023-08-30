import java.util.Scanner;
import monday.ui.Ui;
import task.TaskList;
import monday.parser.Parser;
import monday.exception.MondayExceptions;

/**
 * Monday is a task management ChatBot. Users can add, mark, unmark,
 * delete, keep track of the tasks they have.
 */
public class Monday {
    /**
     * Starts Monday application.
     * Initialises necessary components, greets the user, handle user input and handle errors.
     */
    private static void startMonday(String filepath) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList(filepath);
        boolean running = true;

        Ui.printSeparator();
        Ui.greet();
        Ui.printSeparator();
        while (running) {
            String userInput = scanner.nextLine();
            Ui.printSeparator();
            try {
                running = Parser.mondayParser(userInput, taskList);
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

    /**
     * Entry point to Monday.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        startMonday("./data/duke.txt");
    }
}
