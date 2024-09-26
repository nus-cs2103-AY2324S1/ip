package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The HelpCommand class represents a command to display a help message to the user, listing available commands.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class HelpCommand extends Command {
    /**
     * Executes the "help" command by displaying a list of available commands and their descriptions.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying the help message.
     * @param storage The storage for saving the updated task list to a file (not used in this command).
     * @return A string containing the help message with a list of available commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the available commands:\n");
        message.append("- `list`: List all tasks\n");
        message.append("- `bye`: Exit the program\n");
        message.append("- `hi`: Display a welcome message\n");
        message.append("- `mark <index>`: Mark a task as done\n");
        message.append("- `unmark <index>`: Mark a task as not done\n");
        message.append("- `delete <index>`: Delete a task\n");
        message.append("- `find <keyword>`: Find tasks by keyword\n");
        message.append("- `help`: Display this help message\n");
        message.append("- `todo <description> /priority <TaskPriority> /from <fromDate> /to <toDate>`:Add Todo task\n");
        message.append("- `deadline <description> <TaskPriority> /by <byDate>`: Add a Deadline task\n");
        message.append("- `event <description> <TaskPriority> /at <atDate>`: Add an Event task\n");
        return message.toString();
    }
}
