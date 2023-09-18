package bellcurvegod.parser;

import java.util.ArrayList;

import bellcurvegod.command.ExitCommand;
import bellcurvegod.command.FindCommand;
import bellcurvegod.command.ListTasksCommand;
import bellcurvegod.command.MarkCommand;
import bellcurvegod.command.UnmarkCommand;
import bellcurvegod.exception.BellCurveGodException;
import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
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

        if (input.equals("bye")) {
            return ExitCommand.run();
        } else if (input.equals("list")) {
            return ListTasksCommand.run();
        } else if (words[0].equals("find")) {
            return FindCommand.run(words[1]);
        } else if (words[0].equals("mark")) {
            return MarkCommand.run(getTaskParams(words[1].split(","), tasks));
        } else if (words[0].equals("unmark")) {
            return UnmarkCommand.run(getTaskParams(words[1].split(","), tasks));
        } else if (words[0].equals("delete")) {
            return TaskList.delete(getTaskParams(words[1].split(","), tasks));
        } else {
            try {
                return TaskList.addTask(input);
            } catch (InvalidCommandException e) {
                return e.getMessage();
            } catch (EmptyDescriptionException e) {
                return e.getMessage();
            }
        }
    }

    private static Task[] getTaskParams(String[] taskIndicesAsString, ArrayList<Task> tasks) {
        Task[] taskParams = new Task[taskIndicesAsString.length];
        for (int i = 0; i < taskIndicesAsString.length; i++) {
            taskParams[i] = tasks.get(Integer.parseInt(taskIndicesAsString[i]) - 1);
        }
        return taskParams;
    }
}
