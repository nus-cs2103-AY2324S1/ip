import java.time.format.DateTimeParseException;
import java.util.Scanner;
import vedaexceptions.IncorrectInputException;
import vedaexceptions.NoDescriptionException;

/**
 * Veda is the main class for the chatbot application "Veda".
 *
 * @author Sebastian Tay
 */
public class Veda {

    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList(storage);
    private static final Ui ui = new Ui(new Scanner(System.in));

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
                System.out.println("Unrecognised command.");
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
                final String keyword = Parser.getKeyword(input);

                ui.displayList(
                        tasks.findKeyword(keyword),
                        "Retrieved the following missions containing the keyword \"" + keyword + "\":"
                );

                break;

            default:
                break;
            }
        }
    }

    /**
     * Runs the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        initialise();

        run();
    }
}
