package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hachi.exceptions.DateFormatWrongException;
import hachi.exceptions.HachiException;
import hachi.exceptions.InvalidCommandException;
import hachi.exceptions.NumberOutOfBoundsException;

/**
 * Provides all the parsing methods for the other classes to parse user commands.
 */
public class Parser {

    /**
     * Parses the user command, and returns the appropriate Command object.
     * @param cmd The raw user command.
     * @param taskList The application taskList.
     * @param storage The application storage.
     * @return A Command object that corresponds to the command passed in by the user.
     */
    public static Command parse(String cmd, TaskList taskList, Storage storage)
            throws InvalidCommandException {
        String[] words = cmd.split(" ");
        String commandWord = words[0];
        String[] arguments = Arrays.copyOfRange(words, 1, words.length);

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(arguments, taskList);
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(arguments, taskList, storage);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(arguments, taskList, storage);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments, taskList, storage);
        case TodoCommand.COMMAND_WORD:
            return new TodoCommand(arguments, taskList, storage);
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(arguments, taskList, storage);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(arguments, taskList, storage);
        case SearchdateCommand.COMMAND_WORD:
            return new SearchdateCommand(arguments, taskList);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments, taskList);
        default:
            throw new InvalidCommandException(commandWord);
        }
    }

    /**
     * Returns the index of a given string within a given list of strings.
     *
     * @param word The given string to find the index of.
     * @param arg The list of string within which to search for the given string.
     * @return The index of the given string. If string is not found, returns -1.
     */
    public static int getWordIndex(String word, String[] arg) {
        int index = -1;
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].equals(word)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Converts a list of tasks in string format to a TaskList object.
     * @param ls The list of tasks in string format
     * @return The corresponding TaskList object
     * @throws HachiException If the text is unable to be converted to task
     */
    public static TaskList parseTaskList(List<String> ls) throws HachiException {
        ArrayList<Task> result = new ArrayList<>();
        for (String s : ls) {
            result.add(convertTextToTask(s));
        }
        return new TaskList(result);
    }

    /**
     * Converts a number that is in string format into a number in integer format. Throws an error
     * if the task number exceeds the total number of tasks in the file.
     * @param num The number in string format
     * @param numOfTasks The total number of tasks in the file.
     * @return The number in integer format
     * @throws NumberFormatException If the number passed in cannot be parsed into an integer.
     * @throws NumberOutOfBoundsException If the task number exceeds the total number of tasks in the file.
     */
    public static int parseTaskNumber(String num, int numOfTasks)
            throws NumberFormatException, NumberOutOfBoundsException {
        int number = Integer.parseInt(num);
        if (number > numOfTasks || number < 0) {
            throw new NumberOutOfBoundsException(numOfTasks);
        }
        return number - 1;
    }

    private static Task convertTextToTask(String txt) throws HachiException {
        String[] s = txt.split(" \\| "); // need to escape | character as it means something in regex
        Task temp; // Initialises the Task to be returned

        // set Task to the respective task type
        try {
            switch (s[0]) {
            case "T":
                if (s.length > 3) {
                    throw new HachiException(
                            "Todo stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                temp = new Todo(s[2]);
                break;
            case "D":
                if (s.length > 4) {
                    throw new HachiException(
                            "Deadline stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                try {
                    temp = new Deadline(s[2], LocalDate.parse(s[3]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3]);
                }
                break;
            case "E":
                if (s.length > 5) {
                    throw new HachiException(
                            "Event stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                try {
                    temp = new Event(s[2], LocalDate.parse(s[3]), LocalDate.parse(s[4]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3] + ", " + s[4]);
                }
                break;
            default:
                throw new HachiException("Task code not recognised! May be in the wrong format."
                        + "Please check the file at 'data/tasks.txt'");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new HachiException("Task stored in the wrong format! Please check the file at 'data/tasks.txt'");
        }

        assert s[1].equals("1") || s[1].equals("0") : "Second number should only be 1 or 0";
        // mark task based on '0' or '1' in the file
        if (s[1].equals("1")) {
            temp.mark();
        } else {
            temp.unmark();
        }

        return temp;
    }

}
