package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to list all existing commands
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    private static final String HELP = "clear: Clears all tasks.\n"
            + "deadline: Adds a new deadline.\n"
            + "delete: Deletes the given task.\n"
            + "event: Adds a new event.\n"
            + "exit: Exits the chatbot.\n"
            + "find: Finds all tasks with the given keyword.\n"
            + "help: Displays all commands.\n"
            + "list: Lists all tasks.\n"
            + "mark: Marks the given task as done.\n"
            + "todo: Adds a new todo item.\n"
            + "unmark: Marks the given task as not done yet.\n";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return HELP;
    }
}
