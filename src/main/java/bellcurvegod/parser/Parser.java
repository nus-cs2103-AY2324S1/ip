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
            return MarkCommand.run(tasks.get(Integer.parseInt(words[1]) - 1));
        } else if (words[0].equals("unmark")) {
            return UnmarkCommand.run(tasks.get(Integer.parseInt(words[1]) - 1));
        } else if (words[0].equals("delete")) {
            return TaskList.delete(tasks.get(Integer.parseInt(words[1]) - 1));
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
}
