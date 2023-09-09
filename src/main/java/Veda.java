import java.io.IOException;
import java.time.format.DateTimeParseException;

import exceptions.IncorrectInputException;
import exceptions.NoDescriptionException;
import tasks.Task;

/**
 * Veda is the main class for the chatbot application "Veda".
 *
 * @author Sebastian Tay
 */
public class Veda {
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList(storage);
    private static final Ui ui = new Ui();

    private static boolean hasNotRetrieveTask = true;

    /**
     * Initialises the logic and cached data of the system.
     *
     * @return String containing a welcome message for the user.
     */
    public String initialise() {
        tasks.load();
        hasNotRetrieveTask = false;
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        if (hasNotRetrieveTask) {
            initialise();
        }

        int method = Parser.parse(input); //Get which commands to perform based on user's input

        switch (method) {
        case -1:
            //Unrecognised input
            return ui.getUnrecognisedInputMessage();

        case 0:
            //User wishes to exit the program
            return ui.getExitMessage();

        case 1:
            //User wishes to see listed missions
            return ui.getListOfMissions(tasks.getTasks());


        case 2:
            //User wishes to mark task as done
            try {
                return tasks.markAsDone(Parser.getTargetIndex(input));
            } catch (NumberFormatException e) {
                return "Invalid index! Please ensure you correctly key in your target index.";
            } catch (NoDescriptionException e) {
                return e.toString();
            }

        case 3:
            //User wishes to mark task as undone
            try {
                return tasks.markAsUndone(Parser.getTargetIndex(input));
            } catch (NumberFormatException e) {
                return "Invalid index! Please ensure you correctly key in your target index.";
            } catch (NoDescriptionException e) {
                return e.getMessage();
            }

        case 4:
            //User wishes to delete a task
            try {
                Task task = tasks.deleteTask(Parser.getTargetIndex(input));

                return "Noted. I have removed the following mission:\n" + task;
            } catch (NumberFormatException e) {
                return "Invalid index! Please ensure you correctly key in your target index.";
            } catch (NoDescriptionException e) {
                return e.getMessage();
            } catch (IndexOutOfBoundsException e) {
                return "Invalid index! Please ensure you correctly key in your target index.";
            } catch (IOException e) {
                return "Unable to update file.";
            }

        case 5:
            //User wishes to add a new task
            try {
                return tasks.addTask(Parser.getTask(input));
            } catch (IncorrectInputException e) {
                return e.toString();
            } catch (DateTimeParseException e) {
                return "Ensure your deadline is of the format {dd/MM/yyyy HHmm}";
            }

        case 6:
            //User wishes to find a task by a keyword
            final String keyword = Parser.getKeyword(input);

            return ui.getListOfMissions(
                    tasks.findKeyword(keyword),
                    "Retrieved the following missions containing the keyword \"" + keyword + "\":"
            );

        default:
            break;
        }

        return "";
    }
}
