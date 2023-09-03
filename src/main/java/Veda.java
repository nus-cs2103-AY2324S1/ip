import VedaExceptions.IncorrectInputException;
import VedaExceptions.NoDescriptionException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Veda is the main class for the chatbot application "Veda".
 *
 * @author Sebastian Tay
 */
public class Veda {

    private final static Storage storage = new Storage();
    private final static TaskList tasks = new TaskList(storage);
    private final static Ui ui = new Ui(new Scanner(System.in));

    private static void initialise() {
        ui.welcome();
        tasks.load();
    }

    private static void run() {
        while (true) {
            String input = ui.getInput();

            int method = Parser.parse(input); //Get which commands to perform based on user's input

            switch (method) {
                case -1:
                    //Unrecognised input
                    ui.displayUnrecognisedInput();
                    break;

                case 0:
                    //User wishes to exit the program
                    ui.exit();
                    return;

                case 1:
                    //User wishes to see listed missions
                    ui.displayList(tasks.getTasks());
                    break;

                case 2:
                    //User wishes to mark task as done
                    try {
                        tasks.markAsDone(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    } catch (NoDescriptionException e) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    //User wishes to mark task as undone
                    try {
                        tasks.markUndone(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    } catch (NoDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    //User wishes to delete a task
                    try {
                        tasks.deleteTask(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    } catch (NoDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    //User wishes to add a new task
                    try {
                        tasks.addTask(Parser.getTask(input));
                    } catch (IncorrectInputException e) {
                        System.out.println(e);
                    } catch (DateTimeParseException e) {
                        System.out.println("Ensure your deadline is of the format {dd/MM/yyyy HHmm}");
                    }
                    break;

                case 6:
                    //User wishes to find a task by a keyword
                    final String KEYWORD = Parser.getKeyword(input);

                    ui.displayList(
                            tasks.findKeyword(KEYWORD),
                            "Retrieved the following missions containing the keyword \"" + KEYWORD + "\":"
                    );

            }
        }
    }

    public static void main(String[] args) {
        initialise();

        run();
    }
}
