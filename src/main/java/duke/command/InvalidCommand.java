package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class InvalidCommand extends Command {

    /**
     * To print an error due to the invalid input.
     * @param taskList The TaskList that stores the Task.
     * @param ui The Ui instance that will interact with the user.
     * @param storage The Storage instance that will update the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printInvalidMessage();
    }

    /**
     * To check whether the user wanted to exit the program.
     * @return Boolean value representing whether the user wanted to exit the program.
     */
    @Override
    public boolean isExit(){
        return false;
    }

}
