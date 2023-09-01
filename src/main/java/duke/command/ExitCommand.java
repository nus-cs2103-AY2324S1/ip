package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    /**
     * To exit the program and print the exit message.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    /**
     * To check whether the user wanted to exit the program.
     * @return Boolean value representing whether the user wanted to exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
