import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static Command parse(String cmd) {
        String[] words = cmd.split(" ");
        return new Command(words[0], Arrays.copyOfRange(words, 1, words.length));
    }

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

    public static String parseTaskArguments(String taskType, String[] arg) {
        switch (taskType) {

        case "todo":
            return String.join(" ", arg);
        }
        return "";

    }

    public static TaskList parseTaskList(List<String> ls) throws HachiException {
        ArrayList<Task> result = new ArrayList<>();
        for (String s : ls) {
            result.add(txtToTask(s));
        }
        return new TaskList(result);
    }

    public static int parseTaskNumber(String num, int numOfTasks)
            throws NumberFormatException, NumberOutOfBoundsException {
        int number = Integer.parseInt(num);
        if (number > numOfTasks) {
            throw new NumberOutOfBoundsException(numOfTasks);
        }
        return number - 1;
    }

    private static Task txtToTask(String txt) throws HachiException {
        String[] s = txt.split(" \\| "); // need to escape | character as it means something in regex
        Task temp = null;
        // set Task to the respective task type
        try {
            if (s[0].equals("T")) {
                temp = new Todo(s[2]);
            } else if (s[0].equals("D")) {
                try {
                    temp = new Deadline(s[2], LocalDate.parse(s[3]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3]);
                }
            } else if (s[0].equals("E")) {
                try {
                    temp = new Event(s[2], LocalDate.parse(s[3]), LocalDate.parse(s[4]));
                } catch (DateTimeParseException e) {
                    throw new DateFormatWrongException(s[3] + ", " + s[4]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Task stored in the wrong format! Please check the file at 'data/tasks.txt'");
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