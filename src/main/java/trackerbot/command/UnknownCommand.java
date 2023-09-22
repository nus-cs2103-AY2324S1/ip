package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class UnknownCommand extends Command {
    UnknownCommand() {}

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setError("Unrecognised Command Type. Try another?");
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setError("Unrecognised Command Type. Try another?");
    }
}
