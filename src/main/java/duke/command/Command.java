package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {


    /**
     * To execute the Command.
     * @param tasks The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * To check whether the user wanted to exit the program.
     * @return Boolean value representing whether the user wanted to exit the program.
     */
    public abstract boolean isExit();

}
