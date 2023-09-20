package monday.monday.parser;

import monday.monday.exception.MondayException;
import monday.monday.ui.Ui;
import monday.task.Deadline;
import monday.task.Event;
import monday.task.TaskList;
import monday.task.ToDo;

/**
 * Parser class is responsible for parsing user input and performing the corresponding actions.
 */
public class Parser {

    private final TaskList taskList;

    /**
     * An enumeration of available commands.
     */
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        HI,
        DEFAULT,
    }

    /**
     * Constructs a parser object with given task list.
     *
     * @param taskList tasklist of the chatbot
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param userInput the user input to be parsed
     * @return response from chatbot
     * @throws MondayException if there are errors related to the Monday application
     * @throws IllegalArgumentException if the user input is in the wrong format
     */
    public String parseCommands(String userInput) throws MondayException {
        String[] input = userInput.split(" ", 2);
        String command = input[0];
        String content = input.length > 1 ? input[1] : null;

        Command commandEnum;
        try {
            commandEnum = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandEnum = Command.DEFAULT;
        }

        switch (commandEnum) {
        case HI:
            return Ui.greet();
        case BYE:
            return Ui.exit();
        case LIST:
            return taskList.toString();
        case MARK:
            return markCommand(content);
        case UNMARK:
            return unMarkCommand(content);
        case TODO:
            return toDoCommand(content);
        case DEADLINE:
            return deadLineCommand(content);
        case EVENT:
            return eventCommand(content);
        case DELETE:
            return deleteCommand(content);
        case FIND:
            return findCommand(content);
        case DEFAULT:
            throw new MondayException("Sorry, I do not understand what that means.\n"
                    + "Please provide a valid input/command. e.g todo read book");
        default:
            throw new MondayException("Sorry the ChatBot, please kindly wait for update.");
        }
    }

    /**
     * Command to mark a task as completed.
     *
     * @param content The index of the task to mark as completed.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the index is not provided.
     */
    private String markCommand(String content) throws MondayException {
        if (content == null) {
            throw new MondayException("Mark requires a index to mark the task as completed.");
        }
        int index = Integer.parseInt(content);

        return taskList.mark(index);
    }

    /**
     * Command to mark a task as uncompleted.
     *
     * @param content The index of the task to mark as uncompleted.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the index is not provided.
     */
    private String unMarkCommand(String content) throws MondayException {
        if (content == null) {
            throw new MondayException("UnMark requires a index to mark the task as uncompleted.");
        }

        int index = Integer.parseInt(content);

        return taskList.unMark(index);
    }

    /**
     * Command to add a new todo task to the task list.
     *
     * @param content The description of the todo task.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the description is empty.
     */
    private String toDoCommand(String content) throws MondayException {
        if (content == null) {
            throw new MondayException("The description of a todo cannot be empty.\n"
                    + "Usage: todo (task)");
        }

        return taskList.addToList(new ToDo(content));
    }

    /**
     * Command to add a new deadline task to the task list.
     *
     * @param content The description and date of the deadline task.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the description or date is empty or the format is invalid.
     */
    private String deadLineCommand(String content) throws MondayException {
        try {
            if (content == null) {
                throw new MondayException("The description of a deadline cannot be empty.\n"
                        + "Usage: deadline (task) /by (time)");
            }

            String[] taskDetails = content.split("/by", 2);
            String description = taskDetails[0];
            String date = taskDetails[1];

            return taskList.addToList(new Deadline(description.trim(), date.trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid Format. "
                    + "Usage: deadline (task) /by (time)");
        }
    }

    /**
     * Command to add a new event task to the task list.
     *
     * @param content The description, start time, and end time of the event task.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the description or timings are empty or the format is invalid.
     */
    private String eventCommand(String content) throws MondayException {
        try {
            if (content == null) {
                throw new MondayException("The description of a event cannot be empty.\n"
                        + "Usage: event (task) /from (start time) /to (end time)");
            }

            String[] taskDetails = content.split("/from", 2);
            String description = taskDetails[0];
            String[] taskTiming = taskDetails[1].split("/to", 2);
            String start = taskTiming[0];
            String end = taskTiming[1];

            return taskList.addToList(new Event(description.trim(),
                    start.trim(),
                    end.trim()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid Format. "
                    + "Usage: event (task) /from (start time) /to (end time)");
        }
    }
    /**
     * Command to delete a task from the task list.
     *
     * @param content The index of the task to delete.
     * @return A string indicating the result of the operation.
     * @throws MondayException If the index is not provided.
     */
    private String deleteCommand(String content) throws MondayException {
        if (content == null) {
            throw new MondayException("Delete requires a index to delete the task");
        }
        int index = Integer.parseInt(content);

        return taskList.delete(index);
    }

    /**
     * Command to find tasks matching a keyword.
     *
     * @param content The keyword to search for.
     * @return A string containing the matched tasks.
     * @throws MondayException If the keyword is not provided or the format is invalid.
     */
    private String findCommand(String content) throws MondayException {
        if (content == null) {
            throw new MondayException("Find requires a keyword to find the tasks");
        }

        String[] keywordDetails = content.split(" ");

        if (keywordDetails.length > 1) {
            throw new IllegalArgumentException("Invalid Format. "
                    + "Usage: find (keyword), there should only be one keyword.");
        }

        return taskList.find(keywordDetails[0]);
    }
}
