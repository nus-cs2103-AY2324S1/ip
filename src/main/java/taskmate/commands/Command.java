package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;

/**
 * The Command class is an abstract class that represents a command given by the user.
 */
public abstract class Command {

    protected String commandType;
    protected boolean isExit;

    /**
     * @return a String object representing the command type. Could be Deadline, Delete, Event, Exit, Find, Help, List,
     *     Mark, Todo, Unmark, or Update
     */
    public String getType() {
        assert this.commandType != null;
        return this.commandType;
    }

    /**
     * Returns the boolean instance attribute `isExit` which denotes whether the Command object is a `bye` command.
     * This value is `true` if the instance is of the `ExitCommand` class, and `false` otherwise.
     *
     * @return boolean isExit.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Carries out the command represented by each of the Command child classes
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a `String` that represents the Command instance's command type
     *
     * @return String command type.
     */
    public String getCommandType() {
        assert this.commandType != null;
        return this.commandType;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}
