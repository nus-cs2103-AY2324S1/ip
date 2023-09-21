package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.NotFoundException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Commands;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.SortOrder;
import duke.tasks.SortType;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;


/**
 * Controls the parsing of commands and arguments entered by the user.
 */
public class Parser {
    private static final String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static final String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static final String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static final String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static final String INVALID_DATE_FORMAT = "\uD83D\uDE21 Invalid date format! Try using YYYY-MM-DD HH:mm";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Determines if the program should terminate.
     *
     * @param inputString The user input
     * @return true if the user input is the 'end' command
     */
    public static boolean isTerminateCommand(String inputString) {
        return inputString.equals("bye");
    }

    /**
     * Parses the input string to find the enum command.
     *
     * @param inputString the user's input string
     * @return an enum
     * @throws UnknownCommandException if the input command is unknown or malformed.
     */
    public static Commands getInputCommand(String inputString) throws UnknownCommandException {
        String inputCommandString = (inputString.split(" ")[0].toUpperCase());
        if (!Commands.contains(inputCommandString)) {
            throw new UnknownCommandException();
        }

        return Commands.valueOf(inputCommandString);
    }

    /**
     * Parses the input string by the user.
     *
     * @param inputString The input string entered by the user.
     * @param taskList    The list of tasks
     * @return an array list of modified tasks
     * @throws DukeException the program exception with a custom label based on what went wrong
     */
    public static ArrayList<Task> parseInput(String inputString, TaskList taskList, Duke duke) throws DukeException {

        Commands inputCommand = getInputCommand(inputString);
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        switch (inputCommand) {
        case LIST:
            modifiedTasks = processListCommand(taskList);
            break;
        case MARK: {
            modifiedTasks = processMarkCommand(inputString, taskList);

            break;
        }
        case UNMARK: {
            modifiedTasks = processUnmarkCommand(inputString, taskList);

            break;
        }
        case DELETE: {
            // check if is number
            modifiedTasks = processDeleteCommand(inputString, taskList);

            break;
        }
        case TODO: {
            // add a todo
            modifiedTasks = processTodoCommand(inputString, taskList);
            break;
        }
        case DEADLINE: {
            modifiedTasks = processDeadlineCommand(inputString, taskList);

            break;
        }
        case EVENT: {
            modifiedTasks = processEventCommand(inputString, taskList);

            break;
        }
        case FIND: {
            modifiedTasks = processFindCommand(inputString, taskList);
            break;
        }
        case SORT: {
            processSortCommand(inputString, duke);
            break;
        }

        default:
            throw new UnknownCommandException();
        }

        return modifiedTasks;
    }

    /**
     * Processes the inputted Sort command
     * @param inputString The user input
     * @param duke The chatbot
     * @throws InvalidFormatException If the command was malformed
     */
    private static void processSortCommand(String inputString, Duke duke) throws InvalidFormatException {
        String[] args = inputString.replace("sort ", "").split(" ");
        SortType type = SortType.parseString(args[0]);
        SortOrder order = SortOrder.parseString(args[1]);

        duke.setSort(type, order);
    }

    /**
     * Processes the inputted Find command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the list of tasks found by the Find command
     */
    private static ArrayList<Task> processFindCommand(String inputString, TaskList taskList) {
        ArrayList<Task> modifiedTasks;
        String searchString = inputString.replace("find ", "");

        ArrayList<Task> filtered = taskList.findTasksByName(searchString);

        modifiedTasks = filtered;
        return modifiedTasks;
    }

    /**
     * Processes the inputted create new Event command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was added
     * @throws InvalidFormatException if the input format is incorrect
     */
    private static ArrayList<Task> processEventCommand(
            String inputString, TaskList taskList) throws InvalidFormatException {
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        String inputArgs = inputString.replace("event ", "");

        // sample format: event project meeting /from Mon 2pm /to 4pm
        // get the name
        String itemName = inputArgs.split(" /from ")[0];

        if (itemName.isEmpty()) {
            // no item name
            throw new InvalidFormatException(NAME_EMPTY, inputString);
        }

        // get the 'from...to'
        // @see https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex
        Pattern patternFrom = Pattern.compile("(/from )(.*?)( /to)");
        Matcher matcherFrom = patternFrom.matcher(inputArgs);

        String from = "";

        if (matcherFrom.find()) {
            // yes, formatted correctly
            from = matcherFrom.group(2);
            assert !from.isEmpty() : " Empty from! From should not be empty!";
        } else {
            throw new InvalidFormatException(FROM_EMPTY, inputString);
        }

        // parse the 'from'
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);

        // get the to...
        String to = inputArgs.split("/to ")[1];

        if (to.isEmpty()) {
            throw new InvalidFormatException(TO_EMPTY, inputString);
        }
        // parse the 'to'
        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);

        EventTask eventTask = new EventTask(itemName, dateTimeFrom, dateTimeTo);

        taskList.addToList(eventTask);

        modifiedTasks.add(eventTask);

        return modifiedTasks;
    }

    /**
     * Processes the inputted create Deadline command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was added
     * @throws InvalidFormatException if the input format is incorrect
     */
    private static ArrayList<Task> processDeadlineCommand(
            String inputString, TaskList taskList) throws InvalidFormatException {
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        // format of entry: "deadline return book /by Sunday"
        String itemName = inputString.replace("deadline ", "").split(" /by ")[0];

        if (itemName.isEmpty()) {
            // no item name
            throw new InvalidFormatException(NAME_EMPTY, inputString);
        }

        String[] inputArgs = inputString.replace("deadline ", "").split(" /by ");
        if (inputArgs.length < 2) {
            // missing deadline
            throw new InvalidFormatException(DEADLINE_EMPTY, inputString);
        }
        String deadline = inputArgs[1];

        if (deadline.isEmpty()) {
            // no item name
            throw new InvalidFormatException(DEADLINE_EMPTY, inputString);
        }

        // parse the deadline - should be a LocalDate format

        try {
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);

            DeadlineTask deadlineTask = new DeadlineTask(itemName, deadlineDateTime);

            taskList.addToList(deadlineTask);
            modifiedTasks.add(deadlineTask);

            return modifiedTasks;
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(INVALID_DATE_FORMAT, inputString);
        }
    }

    /**
     * Processes the inputted create Todo command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was added
     * @throws InvalidFormatException if the input format is incorrect
     */
    private static ArrayList<Task> processTodoCommand(
            String inputString, TaskList taskList) throws InvalidFormatException {
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        String itemName = inputString.replace("todo ", "");

        if (itemName.isEmpty()) {
            // no item name
            throw new InvalidFormatException(NAME_EMPTY, inputString);
        }


        TodoTask todoTask = new TodoTask(itemName);

        taskList.addToList(todoTask);
        modifiedTasks.add(todoTask);

        return modifiedTasks;
    }

    /**
     * Processes the inputted Delete task command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was deleted
     * @throws InvalidFormatException if the input format is incorrect
     * @throws NotFoundException if the target task was not found
     */
    private static ArrayList<Task> processDeleteCommand(
            String inputString, TaskList taskList) throws InvalidFormatException, NotFoundException {
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        String[] inputArgs = inputString.split(" ");
        if (inputArgs.length != 2) {
            throw new InvalidFormatException("Invalid format for Delete!", inputString);
        }
        int index = Integer.parseInt(inputArgs[1]);
        Task removedTask = taskList.removeFromList(index);
        modifiedTasks.add(removedTask);

        return modifiedTasks;
    }

    /**
     * Processes the inputted Unmark task command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was unmarked
     * @throws InvalidFormatException if the input format is incorrect
     * @throws NotFoundException if the target task was not found
     */
    private static ArrayList<Task> processUnmarkCommand(
            String inputString, TaskList taskList) throws InvalidFormatException, NotFoundException {
        // check if is number
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        String[] inputArgs = inputString.split(" ");
        if (inputArgs.length != 2) {
            throw new InvalidFormatException("Invalid format for Unmark!", inputString);
        }
        int index = Integer.parseInt(inputArgs[1]);
        Task unmarkedTask = taskList.markAsUnDone(index);
        modifiedTasks.add(unmarkedTask);
        return modifiedTasks;
    }

    /**
     * Processes the inputted Mark task command
     * @param inputString The user input
     * @param taskList The active tasks
     * @return the task that was marked
     * @throws InvalidFormatException if the input format is incorrect
     * @throws NotFoundException if the target task was not found
     */
    private static ArrayList<Task> processMarkCommand(
            String inputString, TaskList taskList) throws InvalidFormatException, NotFoundException {
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        String[] inputArgs = inputString.split(" ");
        if (inputArgs.length != 2) {
            throw new InvalidFormatException("Invalid format for Mark!", inputString);
        }
        int index = Integer.parseInt(inputArgs[1]);
        Task markedTask = taskList.markAsDone(index);
        modifiedTasks.add(markedTask);
        return modifiedTasks;
    }

    /**
     * Processes the inputted List command
     * @param taskList The active tasks
     * @return the list of tasks that the user has
     */
    private static ArrayList<Task> processListCommand(TaskList taskList) {
        ArrayList<Task> modifiedTasks;
        modifiedTasks = taskList.getTasks();
        return modifiedTasks;
    }


}
