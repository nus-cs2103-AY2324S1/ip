package bellcurvegod.parser;

import java.util.ArrayList;

import bellcurvegod.command.AddTaskCommand;
import bellcurvegod.command.DeleteTaskCommand;
import bellcurvegod.command.ExitCommand;
import bellcurvegod.command.FindCommand;
import bellcurvegod.command.HelpCommand;
import bellcurvegod.command.ListTasksCommand;
import bellcurvegod.command.MarkCommand;
import bellcurvegod.command.UnmarkCommand;
import bellcurvegod.exception.BellCurveGodException;
import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.exception.TaskDoesNotExistException;
import bellcurvegod.exception.TooManySpacesException;
import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the parser to parse user input.
 */
public class Parser {
    /**
     * Parses commands entered by the user,
     * and exits when the user types the command bye.
     *
     * @param input input string to be parsed.
     * @throws BellCurveGodException If any exception is thrown.
     */
    public static String parse(String input) throws BellCurveGodException {
        ArrayList<Task> tasks = TaskList.getTaskList();

        String[] words = input.split(" ");
        if (hasTooManySpaces(words)) {
            throw new TooManySpacesException("You've entered too many spaces between each words. "
                + "Please ensure there is only one space between each word.\n");
        }

        if (input.equals("bye")) {
            return ExitCommand.run();
        } else if (input.equals("help") | words[0].equals("help")) {
            return HelpCommand.run(input);
        } else if (input.equals("list")) {
            return ListTasksCommand.run();
        } else if (words[0].equals("find")) {
            return FindCommand.run(words[1]);
        } else if (words[0].equals("mark")) {
            return MarkCommand.run(getTaskParams(words[1].split(","), tasks));
        } else if (words[0].equals("unmark")) {
            return UnmarkCommand.run(getTaskParams(words[1].split(","), tasks));
        } else if (words[0].equals("delete")) {
            return DeleteTaskCommand.run(getTaskParams(words[1].split(","), tasks));
        } else {
            try {
                return AddTaskCommand.run(input);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                return e.getMessage();
            }
        }
    }

    private static Task[] getTaskParams(String[] taskIndicesAsString, ArrayList<Task> tasks)
            throws TaskDoesNotExistException, InvalidCommandException {
        Task[] taskParams = new Task[taskIndicesAsString.length];
        for (int i = 0; i < taskIndicesAsString.length; i++) {
            try {
                Integer.parseInt(taskIndicesAsString[i]);
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("You have entered non-integer as indices. "
                    + "Please check your input.\n");
            }
            int index = Integer.parseInt(taskIndicesAsString[i]);
            if (index > tasks.size()) {
                throw new TaskDoesNotExistException("There is no task with index " + index + " in your task list. "
                + "Please check your input.\n");
            }
            taskParams[i] = tasks.get(index - 1);
        }
        return taskParams;
    }

    private static boolean hasTooManySpaces(String[] words) {
        for (String word : words) {
            if (word.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
