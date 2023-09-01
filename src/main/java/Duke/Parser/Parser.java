package Duke.Parser;

import Duke.Exceptions.DukeException;
import Duke.Storage.Storage;
import Duke.Tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Duke.UI.Ui.printResult;

/**
 * Controls the parsing of commands and arguments entered by the user.
 */
public class Parser {
    private static String UNKNOWN_COMMAND = "\uD83D\uDE21 This command is not something I can handle!";

    private static String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static String TIME_FORMAT_ERROR = "\uD83D\uDE21 Time format invalid!";
    private static String INVALID_DATE_FORMAT = "\uD83D\uDE21 Invalid date format! Try using YYYY-MM-DD";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the input string by the user.
     *
     * @param inputString The input string entered by the user.
     * @param taskList The list of tasks
     * @param storage The storage object that handles saving updates.
     *
     * @return true if the program can continue, false if the program has to halt.
     * @throws DukeException
     */
    public static boolean parse(String inputString, TaskList taskList, Storage storage) throws DukeException {
        String inputCommandString = (inputString.split(" ")[0].toUpperCase());
        if (!Commands.contains(inputCommandString)) {
            throw new DukeException(UNKNOWN_COMMAND);
        }

        Commands inputCommand = Commands.valueOf(inputCommandString);

        boolean canContinue;
        switch (inputCommand) {
            case BYE: {
                printResult(inputCommand, null, taskList);

                canContinue = false;
                break;
            }
            case LIST: {
                printResult(inputCommand, null, taskList);

                canContinue =  true;
                break;

            }
            case MARK: {
                // check if is number
                int index = Integer.parseInt(inputString.split(" ")[1]);
                Task markedTask = taskList.markAsDone(index);

                printResult(inputCommand, markedTask, taskList);
                canContinue =  true;
                break;
            }
            case UNMARK: {
                int index = Integer.parseInt(inputString.split(" ")[1]);
                Task unmarkedTask = taskList.markAsUnDone(index);

                printResult(inputCommand, unmarkedTask, taskList);
                canContinue =  true;
                break;
            }
            case DELETE: {
                int index = Integer.parseInt(inputString.split(" ")[1]);
                Task removedTask = taskList.removeFromList(index);

                printResult(inputCommand, removedTask, taskList);
                canContinue =  true;
                break;
            }
            case TODO: {
                // add a todo
                String itemName = inputString.replace("todo ", "");

                if (itemName.isEmpty()) {
                    // no item name
                    throw new DukeException(NAME_EMPTY);
                }


                TodoTask todoTask = new TodoTask(itemName);

                taskList.addToList(todoTask);

                printResult(inputCommand, todoTask, taskList);
                canContinue =  true;
                break;
            }
            case DEADLINE: {
                // format of entry: "deadline return book /by Sunday"
                String itemName = inputString.replace("deadline ", "").split(" /by ")[0];

                if (itemName.isEmpty()) {
                    // no item name
                    throw new DukeException(NAME_EMPTY);
                }

                String[] inputArgs = inputString.replace("deadline ", "").split(" /by ");
                if (inputArgs.length < 2) {
                    // missing deadline
                    throw new DukeException(DEADLINE_EMPTY);
                }
                String deadline = inputArgs[1];

                if (deadline.isEmpty()) {
                    // no item name
                    throw new DukeException(DEADLINE_EMPTY);
                }

                // parse the deadline - should be a LocalDate format

                try {
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);

                    DeadlineTask deadlineTask = new DeadlineTask(itemName, deadlineDateTime);

                    taskList.addToList(deadlineTask);

                    printResult(inputCommand, deadlineTask, taskList);
                } catch (DateTimeParseException e) {
                    throw new DukeException(INVALID_DATE_FORMAT);
                }


                canContinue =  true;
                break;
            }
            case EVENT: {
                String inputArgs = inputString.replace("event ", "");

                // sample format: event project meeting /from Mon 2pm /to 4pm
                // get the name
                String itemName = inputArgs.split(" /from ")[0];

                if (itemName.isEmpty()) {
                    // no item name
                    throw new DukeException(NAME_EMPTY);
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
                    throw new DukeException(FROM_EMPTY);
                }

                // parse the 'from'
                LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);


                // get the to...
                String to = inputArgs.split("/to ")[1];

                if (to.isEmpty()) {
                    throw new DukeException(TO_EMPTY);
                }
                // parse the 'to'
                LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);

                EventTask eventTask = new EventTask(itemName, dateTimeFrom, dateTimeTo);

                taskList.addToList(eventTask);

                printResult(inputCommand, eventTask, taskList);
                canContinue =  true;
                break;
            }

            default:
                throw new DukeException(UNKNOWN_COMMAND);
        }

        storage.saveTasks(taskList);

        return canContinue;
    }
}
