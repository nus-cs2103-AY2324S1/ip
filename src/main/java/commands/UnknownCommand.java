package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

/**
 * Represents an unknown command.
 */
public class UnknownCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
