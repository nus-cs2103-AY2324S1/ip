package duke.parser;

import static duke.ui.Ui.printResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.UnknownCommandException;
import duke.storage.Storage;
import duke.tasks.Commands;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.TodoTask;


/**
 * Controls the parsing of commands and arguments entered by the user.
 */
public class Parser {
    private static final String BREAK_LOOP = "--BREAK--";
    private static final String UNKNOWN_COMMAND = "\uD83D\uDE21 This command is not something I can handle!";

    private static final String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static final String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static final String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static final String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static final String TIME_FORMAT_ERROR = "\uD83D\uDE21 Time format invalid!";
    private static final String INVALID_DATE_FORMAT = "\uD83D\uDE21 Invalid date format! Try using YYYY-MM-DD";

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

        Commands inputCommand = Commands.valueOf(inputCommandString);

        return inputCommand;
    }

    /**
     * Parses the input string by the user.
     *
     * @param inputString The input string entered by the user.
     * @param taskList    The list of tasks
     * @param storage     The storage object that handles saving updates.
     * @return true if the program can continue, false if the program has to halt.
     * @throws DukeException
     */
    public static ArrayList<Task> parseInput(String inputString, TaskList taskList, Storage storage) throws DukeException {

        Commands inputCommand = getInputCommand(inputString);
        ArrayList<Task> modifiedTasks = new ArrayList<>();
        switch (inputCommand) {
        case LIST: {
//            printResult(inputCommand, null, taskList);
            modifiedTasks = taskList.getTasks();
            break;
        }
        case MARK: {
            // check if is number
            int index = Integer.parseInt(inputString.split(" ")[1]);
            Task markedTask = taskList.markAsDone(index);
            modifiedTasks.add(markedTask);
//            printResult(inputCommand, markedTask, taskList);
            break;
        }
        case UNMARK: {
            int index = Integer.parseInt(inputString.split(" ")[1]);
            Task unmarkedTask = taskList.markAsUnDone(index);
            modifiedTasks.add(unmarkedTask);
//            printResult(inputCommand, (unmarkedTask), taskList);
            break;
        }
        case DELETE: {
            int index = Integer.parseInt(inputString.split(" ")[1]);
            Task removedTask = taskList.removeFromList(index);
            modifiedTasks.add(removedTask);
//            printResult(inputCommand, removedTask, taskList);
            break;
        }
        case TODO: {
            // add a todo
            String itemName = inputString.replace("todo ", "");

            if (itemName.isEmpty()) {
                // no item name
                throw new InvalidFormatException(NAME_EMPTY, inputString);
            }


            TodoTask todoTask = new TodoTask(itemName);

            taskList.addToList(todoTask);
            modifiedTasks.add(todoTask);
//            printResult(inputCommand, (todoTask), taskList);
            break;
        }
        case DEADLINE: {
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
//                printResult(inputCommand, (deadlineTask), taskList);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException(INVALID_DATE_FORMAT, inputString);
            }


            break;
        }
        case EVENT: {
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
//            printResult(inputCommand, (eventTask), taskList);
            break;
        }
        case FIND: {
            String searchString = inputString.replace("find ", "");

            ArrayList<Task> filtered = taskList.findTasksByName(searchString);

            modifiedTasks = filtered;
//            printResult(inputCommand, null, new TaskList(filtered));
            break;
        }

        default:
            throw new UnknownCommandException();
        }

        return modifiedTasks;

    }

//    public static String getResponse(String inputString, TaskList taskList, Storage storage) throws DukeException {
//
//
//        Commands inputCommand = getInputCommand(inputString);
//
//        switch (inputCommand) {
//        case BYE: {
//            printResult(inputCommand, null, taskList);
//
//
//            break;
//        }
//        case LIST: {
//            printResult(inputCommand, null, taskList);
//
//
//            break;
//
//        }
//        case MARK: {
//            // check if is number
//            int id = Integer.parseInt(inputString.split(" ")[1]);
//            Task markedTask = taskList.markAsDone(id);
//
//            printResult(inputCommand, markedTask, taskList);
//
//            break;
//        }
//        case UNMARK: {
//            int id = Integer.parseInt(inputString.split(" ")[1]);
//            Task unmarkedTask = taskList.markAsUnDone(id);
//
//            printResult(inputCommand, (unmarkedTask), taskList);
//
//            break;
//        }
//        case DELETE: {
//            int id = Integer.parseInt(inputString.split(" ")[1]);
//            Task removedTask = taskList.removeFromList(id);
//
//            printResult(inputCommand, removedTask, taskList);
//
//            break;
//        }
//        case TODO: {
//            // add a todo
//            String itemName = inputString.replace("todo ", "");
//
//            if (itemName.isEmpty()) {
//                // no item name
//                throw new DukeException(NAME_EMPTY);
//            }
//
//
//            TodoTask todoTask = new TodoTask(itemName);
//
//            taskList.addToList(todoTask);
//
//            printResult(inputCommand, (todoTask), taskList);
//
//            break;
//        }
//        case DEADLINE: {
//            // format of entry: "deadline return book /by Sunday"
//            String itemName = inputString.replace("deadline ", "").split(" /by ")[0];
//
//            if (itemName.isEmpty()) {
//                // no item name
//                throw new DukeException(NAME_EMPTY);
//            }
//
//            String[] inputArgs = inputString.replace("deadline ", "").split(" /by ");
//            if (inputArgs.length < 2) {
//                // missing deadline
//                throw new DukeException(DEADLINE_EMPTY);
//            }
//            String deadline = inputArgs[1];
//
//            if (deadline.isEmpty()) {
//                // no item name
//                throw new DukeException(DEADLINE_EMPTY);
//            }
//
//            // parse the deadline - should be a LocalDate format
//
//            try {
//                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
//
//                DeadlineTask deadlineTask = new DeadlineTask(itemName, deadlineDateTime);
//
//                taskList.addToList(deadlineTask);
//
//                printResult(inputCommand, (deadlineTask), taskList);
//            } catch (DateTimeParseException e) {
//                throw new DukeException(INVALID_DATE_FORMAT);
//            }
//
//
//            break;
//        }
//        case EVENT: {
//            String inputArgs = inputString.replace("event ", "");
//
//            // sample format: event project meeting /from Mon 2pm /to 4pm
//            // get the name
//            String itemName = inputArgs.split(" /from ")[0];
//
//            if (itemName.isEmpty()) {
//                // no item name
//                throw new DukeException(NAME_EMPTY);
//            }
//
//            // get the 'from...to'
//            // @see https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex
//            Pattern patternFrom = Pattern.compile("(/from )(.*?)( /to)");
//            Matcher matcherFrom = patternFrom.matcher(inputArgs);
//
//            String from = "";
//            if (matcherFrom.find()) {
//                // yes, formatted correctly
//                from = matcherFrom.group(2);
//            } else {
//                throw new DukeException(FROM_EMPTY);
//            }
//
//            // parse the 'from'
//            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
//
//
//            // get the to...
//            String to = inputArgs.split("/to ")[1];
//
//            if (to.isEmpty()) {
//                throw new DukeException(TO_EMPTY);
//            }
//            // parse the 'to'
//            LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
//
//            EventTask eventTask = new EventTask(itemName, dateTimeFrom, dateTimeTo);
//
//            taskList.addToList(eventTask);
//
//            printResult(inputCommand, (eventTask), taskList);
//
//            break;
//        }
//        case FIND: {
//            String searchString = inputString.replace("find ", "");
//
//            ArrayList<Task> filtered = taskList.findTasksByName(searchString);
//
//
//            printResult(inputCommand, null, new TaskList(filtered));
//
//            break;
//        }
//
//        default:
//            throw new DukeException(UNKNOWN_COMMAND);
//        }
//
//        storage.saveTasks(taskList);
//
//        return "";
//    }
}
