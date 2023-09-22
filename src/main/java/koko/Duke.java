package koko;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the main driver class of the Koko chatbot application.
 * Initializes and manages the user interface, storage, parser, and task list.
 */
public class Duke {

    private static final String CHATBOT_NAME = "Koko";
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    private TaskList taskList;

    private String startupFileLoadMessage = "";

    /**
     * Constructs a Duke instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui(CHATBOT_NAME);
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            taskList = storage.loadTasksFromFile();
            startupFileLoadMessage = ui.showLoadedTasks(taskList);

        } catch (FileNotFoundException fileNotFoundException) {
            startupFileLoadMessage = ui.generateErrorMessage(
                    "Previous data file not found, starting from fresh task list.");
            taskList = new TaskList();
        } catch (DukeException dukeException) {
            startupFileLoadMessage = ui.generateErrorMessage(dukeException.getMessage());
            taskList = new TaskList();
        }
    }

    public String generateStartupMessage() {
        return ui.greet() + "\n" + startupFileLoadMessage;
    }


    /**
     * Handles user input, parses it and dispatches the command to perform the appropriate actions.
     *
     * @param input The raw input string from the user.
     *
     * @return A string representing the output of the command.
     */
    public String handleInputAndDispatch(String input) {
        String output;

        try {
            if (parser.hasInvalidCharacters(input)) {
                throw new DukeException("Input cannot contain the character '|'");
            }

            Command commandType = parser.parseCommandType(input);
            String remaining = parser.parseRemainingArgs(commandType, input);

            switch (commandType) {
            case BYE:
                output = null;
                break;
            case LIST:
                output = ui.generateTaskListOutput(taskList);
                break;
            case MARK:
                int markIndex = Integer.parseInt(remaining) - 1;
                Task markedTask = taskList.markTaskAtIndex(markIndex);
                output = ui.generateTaskMarkedMessage(markedTask);
                break;
            case UNMARK:
                int unmarkIndex = Integer.parseInt(remaining) - 1;
                Task unmarkedTask = taskList.unmarkTaskAtIndex(unmarkIndex);
                output = ui.generateTaskUnmarkedMessage(unmarkedTask);
                break;
            case DELETE:
                Task deletedTask = taskList.deleteTaskAtIndex(Integer.parseInt(remaining) - 1);
                output = ui.generateTaskDeletedMessage(deletedTask, taskList.size());
                break;
            case TODO:
                Parser.ParsedTodoArgs parsedTodoArgs = parser.parseTodoString(remaining);
                Todo newTodo = new Todo(parsedTodoArgs.taskName);
                taskList.addTask(newTodo);
                output = ui.generateTaskAddedMessage(newTodo, taskList.size());
                break;
            case DEADLINE:
                Parser.ParsedDeadlineArgs parsedDeadlineArgs = parser.parseDeadlineString(remaining);
                Deadline newDeadline = new Deadline(parsedDeadlineArgs.taskName,
                        parsedDeadlineArgs.byDate);
                taskList.addTask(newDeadline);
                output = ui.generateTaskAddedMessage(newDeadline, taskList.size());
                break;
            case EVENT:
                Parser.ParsedEventArgs parsedEventArgs = parser.parseEventString(remaining);
                Event newEvent = new Event(parsedEventArgs.taskName, parsedEventArgs.startDate,
                        parsedEventArgs.endDate);
                taskList.addTask(newEvent);
                output = ui.generateTaskAddedMessage(newEvent, taskList.size());
                break;
            case FIND:
                String searchTerm = remaining.trim();
                TaskList matchingTasks = taskList.findTasksFromKeyword(searchTerm);
                output = ui.showMatchingTasks(matchingTasks);
                break;
            default:
                throw new DukeException("Each message should start with one of the following commands: "
                        + "list, mark, unmark, todo, deadline, event, find");
            }

            // All valid commands except for `list` will result in the task list changing, so we should trigger
            // a disk write to save the updated task list.
            if (!commandType.equals(Command.LIST)) {
                try {
                    storage.saveTasksToFile(taskList);
                } catch (IOException ioException) {
                    throw new DukeException("Error while saving task list to file!");
                }
            }

        } catch (NumberFormatException e) {
            output = ui.generateErrorMessage("Please enter a valid task number.");
        } catch (DukeException e) {
            output = ui.generateErrorMessage(e.getMessage());
        }

        return output;
    }

}
