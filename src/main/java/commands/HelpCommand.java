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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Sorry, I don't understand what do you mean.");
        ui.showMessage("Here are some sample usages for your reference:");
        ui.showMessage("1. Add a deadline to your list: deadline + (description) + (deadline)");
        ui.showMessage("2. Add a todo task to your list: todo + (description)");
        ui.showMessage("3. Add an event task to your list: event + (description) + from + "
          + "(startdate) + to + (enddate)");
        ui.showMessage("4. Delete a task: delete + (line number)");
        ui.showMessage("5. Mark a task: mark + (line number)");
        ui.showMessage("6. Unmark a class: unmark + (line number)");
        ui.showMessage("content in ( ) is for you to fill out");
    }
}
