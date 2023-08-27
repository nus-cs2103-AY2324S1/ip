package rat.command;

import rat.tasks.RatTaskManager;

public abstract class ModifyCommand extends RatCommand {

    public ModifyCommand(RatTaskManager ratTaskManager) {
        super(ratTaskManager);
    }

    @Override
    public abstract void execute();
}
