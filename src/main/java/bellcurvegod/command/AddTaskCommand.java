package bellcurvegod.command;

import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.tasklist.TaskList;

/**
 * Encapsulates the AddTaskCommand.
 */
public class AddTaskCommand {
    /**
     * Adds the task specified by the input.
     *
     * @param input user input.
     * @return message after successfully adding a task.
     * @throws InvalidCommandException   If the input does not start with any of the Task type.
     * @throws EmptyDescriptionException If description is missing.
     */
    public static String run(String input) throws InvalidCommandException, EmptyDescriptionException {
        return TaskList.addTask(input);
    }

    public static String getHelpMessage(String cmd) {
        switch (cmd) {
        case "todo":
            return "Type 'todo <description>' and enter, "
                + "the app will create a task with the given description.\n"
                + "e.g. todo read book\n";
        case "deadline":
            return "Type 'deadline <description> /by <deadline date>' and enter, "
                + "the app will create a task with the given description and deadline date.\n"
                + "e.g. deadline return book /by 2023-09-20\n";
        case "event":
            return "Type 'event <description> /from <start date> /to <end date>' and enter, "
                + "the app will create a task with the given description, start date and end date.\n"
                + "e.g. event tournament /from 2023-09-20 /to 2023-09-30\n";
        default:
            return "There is no command named" + cmd + "\n";
        }
    }
}
