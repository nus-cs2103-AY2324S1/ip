package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * A command class that will be executed to perform an action made by the user
 */
public abstract class Command {

    public Command() {
    }
    public abstract String execute(Storage storage, Ui ui, TaskList taskList);
}
