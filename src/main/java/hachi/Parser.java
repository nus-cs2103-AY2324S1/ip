package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.DateFormatWrongException;
import exceptions.EmptyNumberException;
import exceptions.EmptyTaskException;
import exceptions.HachiException;
import exceptions.NumberOutOfBoundsException;
import exceptions.TooManyArgumentsException;

/**
 * Provides all the parsing methods for the other classes to parse user commands.
 */
public class Parser {

    /**
     * Splits the raw user command into two parts, the main command word and an array of the arguments.
     * @param cmd The raw user command
     * @return A Command object that encapsulates both the main command word and the array of arguments.
     */
    public static Command parse(String cmd) {
        String[] words = cmd.split(" ");
        return new Command(words[0], Arrays.copyOfRange(words, 1, words.length));
    }

    /**
     * Throws the appropriate error if the argument length is wrong. Otherwise, does nothing.
     * @param commandWord The command word whose arguments we are checking.
     * @param argumentLength The number of arguments which the user passed in.
     * @throws HachiException The appropriate exception if argument length is wrong.
     */
    public static void checkArgumentLength(String commandWord, int argumentLength)
            throws HachiException {
        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            if (argumentLength > 0) {
                throw new TooManyArgumentsException(ByeCommand.COMMAND_WORD, 0, argumentLength);
            }
            break;
        case ListCommand.COMMAND_WORD:
            if (argumentLength > 0) {
                throw new TooManyArgumentsException(ListCommand.COMMAND_WORD, 0, argumentLength);
            }
            break;
        case MarkCommand.COMMAND_WORD:
            if (argumentLength > 1) {
                throw new TooManyArgumentsException(MarkCommand.COMMAND_WORD, 1, argumentLength);
            }
            if (argumentLength < 1) {
                throw new EmptyNumberException(MarkCommand.COMMAND_WORD);
            }
            break;
        case UnmarkCommand.COMMAND_WORD:
            if (argumentLength > 1) {
                throw new TooManyArgumentsException(UnmarkCommand.COMMAND_WORD, 1, argumentLength);
            }
            if (argumentLength < 1) {
                throw new EmptyNumberException(UnmarkCommand.COMMAND_WORD);
            }
            break;
        case DeleteCommand.COMMAND_WORD:
            if (argumentLength > 1) {
                throw new TooManyArgumentsException(DeleteCommand.COMMAND_WORD, 1, argumentLength);
            }
            if (argumentLength < 1) {
                throw new EmptyNumberException(DeleteCommand.COMMAND_WORD);
            }
            break;
        case TodoCommand.COMMAND_WORD:
            if (argumentLength < 1) {
                throw new EmptyTaskException(TodoCommand.COMMAND_WORD);
            }
            break;
        case DeadlineCommand.COMMAND_WORD:
            if (argumentLength < 1) {
                throw new EmptyTaskException(DeadlineCommand.COMMAND_WORD);
            }
            break;
        case EventCommand.COMMAND_WORD:
            if (argumentLength < 1) {
                throw new EmptyTaskException(EventCommand.COMMAND_WORD);
            }
            break;
        case SearchdateCommand.COMMAND_WORD:
            if (argumentLength > 1) {
                throw new TooManyArgumentsException(SearchdateCommand.COMMAND_WORD, 1, argumentLength);
            }
            if (argumentLength < 1) {
                throw new DateFormatWrongException("");
            }
            break;
        case FindCommand.COMMAND_WORD:
            if (argumentLength < 1) {
                throw new HachiException("Please include the word or words you are searching for!");
            }
            break;
        default:
            break;
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
     * Parses the array of arguments passed in, depending on the type of task the arguments are for.
     * @param taskType The type of task for which to parse the arguments for.
     * @param arg The array of arguments to be parsed.
     * @return The final parsed string.
     */
    public static String parseTaskArguments(String taskType, String[] arg) {
        switch (taskType) {

        default:
            return String.join(" ", arg);
        }

    }

    /**
     * Converts a list of tasks in string format to a TaskList object.
     * @param ls The list of tasks in string format
     * @return The corresponding TaskList object
     * @throws HachiException
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
        if (number > numOfTasks) {
            throw new NumberOutOfBoundsException(numOfTasks);
        }
        return number - 1;
    }

    private static Task convertTextToTask(String txt) throws HachiException {
        String[] s = txt.split(" \\| "); // need to escape | character as it means something in regex
        Task temp = null;
        // set Task to the respective task type
        try {
            if (s[0].equals("T")) {
                if (s.length > 3) {
                    throw new HachiException(
                            "Todo stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                temp = new Todo(s[2]);
            } else if (s[0].equals("D")) {
                if (s.length > 4) {
                    throw new HachiException(
                            "Deadline stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                try {
                    temp = new Deadline(s[2], LocalDate.parse(s[3]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3]);
                }
            } else if (s[0].equals("E")) {
                if (s.length > 5) {
                    throw new HachiException(
                            "Event stored in the wrong format! Please check the file at 'data/tasks.txt'");
                }
                try {
                    temp = new Event(s[2], LocalDate.parse(s[3]), LocalDate.parse(s[4]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3] + ", " + s[4]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new HachiException("Task stored in the wrong format! Please check the file at 'data/tasks.txt'");
        }

        // mark task based on '0' or '1' in the file
        if (s[1].equals("1")) {
            temp.mark();
        } else {
            temp.unmark();
        }

        return temp;
    }

}
