package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to display help information and usage examples.
 */
public class HelpCommand implements Command {
    /**
     * Executes the command to display help information and usage examples to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display help messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Sorry, I don't understand what you mean."
            + "\nHere are some sample usages for your reference:"
            + "\nAdd a deadline to your list: deadline + (description) + by: + (deadline time)\n"
            + "\nAdd a todo task to your list: todo + (description)\n"
            + "\nAdd an event task to your list: event + (description) + from + \n"
            + "(startdate) + to + (enddate)\n"
            + "\nDelete a task: delete + (line number)\n"
            + "\nMark a task: mark + (line number)\n"
            + "\nUnmark a class: unmark + (line number)\n"
            + "\ncontent in ( ) is for you to fill out";
        ui.showMessage(message);
        return message;
    }
}
