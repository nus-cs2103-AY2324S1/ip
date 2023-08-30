package Command;

import Exception.*;
import Helper.*;
import Task.*;

/**
 * Represents a Command that the User has entered through the Command Line Interface.
 */
public abstract class Command {
    private int index = -1;

    public Command(int index) {
        this.index = index;
    }

    /**
     * Returns if the Command is exiting the ChatBot.
     * @return False
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the index given by the User to change in the TaskList.
     * @return index in TaskList
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Executes the command demanded by the User through the Command Line Interface.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
