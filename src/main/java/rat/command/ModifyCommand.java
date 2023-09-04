package rat.command;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that modifies the task list.
 * A modification is either a marking or unmarking of a task as done/undone.
 */
public abstract class ModifyCommand extends RatCommand {

    /**
     * Constructor for a ModifyCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     */
    public ModifyCommand(RatTaskManager ratTaskManager) {
        super(ratTaskManager);
    }

    @Override
    public abstract String getResponse();

    /**
     * Executes the command.
     */
    @Override
    public abstract void execute();
}
