import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import exceptions.IncorrectInputException;
import exceptions.SavedDataFormatException;
import exceptions.UpdateDataException;
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
    public static final String END_CHAT_MESSAGE = ui.getExitMessage();

    /**
     * Initialises the logic and cached data of the system.
     *
     * @return String containing a welcome message for the user.
     */
    public String initialise() {
        try {
            tasks.load();
        } catch (FileNotFoundException | SavedDataFormatException e) {
            return e.toString();
        } catch (StringIndexOutOfBoundsException e) {
            return "Error in parsing saved data.";
        }

        hasNotRetrieveTask = false;
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        if (hasNotRetrieveTask) {
            initialise();
        }

        int method = Parser.parse(input); //Get which commands to perform based on user's input

        return output(method, input.toLowerCase());
    }

    /**
     * Returns a message from performing the tasks based on method and input.
     *
     * @param method is an integer indicating what actions to perform.
     * @param input is the user input
     * @return
     */
    private String output(int method, String input) {
        final String errorInputResponse = ui.getUnrecognisedInputMessage();

        switch (method) {
        case 0:
            return ui.getExitMessage();

        case 1:
            return ui.getListOfMissions(tasks.getTasks());

        case 2:
            return markTaskAsDone(input);

        case 3:
            return markTaskAsUndone(input);

        case 4:
            return deleteTask(input);

        case 5:
            return addTask(input);

        case 6:
            return getListOfMission(input);

        case 7:
            return editTask(input);

        default:
            return errorInputResponse;
        }
    }

    private String markTaskAsDone(String input) {
        try {
            return tasks.markAsDone(Parser.getTargetIndex(input));
        } catch (NumberFormatException e) {
            return "Invalid index! Please ensure you correctly key in your target index.";
        } catch (IncorrectInputException e) {
            return e.toString();
        }
    }

    private String markTaskAsUndone(String input) {
        try {
            return tasks.markAsUndone(Parser.getTargetIndex(input));
        } catch (NumberFormatException e) {
            return "Invalid index! Please ensure you correctly key in your target index.";
        } catch (IncorrectInputException e) {
            return e.getMessage();
        }
    }

    private String deleteTask(String input) {
        try {
            Task task = tasks.deleteTask(Parser.getTargetIndex(input));

            return "Noted. I have removed the following mission:\n" + task;
        } catch (NumberFormatException e) {
            return "Invalid index! Please ensure you correctly key in your target index.";
        } catch (IncorrectInputException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Invalid index! Your target index is within range.";
        } catch (IOException e) {
            return "Unable to update file.";
        } catch (UpdateDataException e) {
            return e.toString();
        }
    }

    private String addTask(String input) {
        try {
            return tasks.addTask(Parser.getTask(input));
        } catch (IncorrectInputException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "Ensure your deadline is of the format {dd/MM/yyyy HHmm}";
        }
    }

    private String getListOfMission(String input) {
        final String keyword = Parser.getKeyword(input);
        final String message = "Retrieved the following missions containing the keyword \"" + keyword + "\":";

        return ui.getListOfMissions(tasks.findKeyword(keyword), message);
    }

    private String editTask(String input) {
        if (input.trim().equals("update")) {
            return "Please key in the task index and new details!";
        }

        try {
            final int taskIndex = Parser.getTargetIndex(input);
            final String newDetails = Parser.parseEditNewDetails(input);

            return tasks.editTask(taskIndex, newDetails);
        } catch (IncorrectInputException | NumberFormatException e) {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
