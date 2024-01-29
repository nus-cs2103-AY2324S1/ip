package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;

/**
 * Represents the exit command
 */
public class ExitCommand extends Command {

    /**
     * Encapsulates the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @return the reply of Quack
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        storage.close();
        return Ui.GOODBYE_MESSAGE;
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Checks if it is the exact same command
     *
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {

        return other instanceof ExitCommand;
    }
}
