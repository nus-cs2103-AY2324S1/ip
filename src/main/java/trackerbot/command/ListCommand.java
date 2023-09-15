package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class ListCommand extends Command {
    ListCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setMessage(tasks.list());
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        // TODO: Implement the mass operation
    }
}
