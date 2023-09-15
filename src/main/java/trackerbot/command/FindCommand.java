package trackerbot.command;

import trackerbot.gui.UiHandler;
import trackerbot.task.TaskList;

class FindCommand extends Command {
    private final String commandFields;

    FindCommand(String commandFields) {
        this.commandFields = commandFields;
    }

    @Override
    public void execute(TaskList tasks, UiHandler uiHandler) {
        uiHandler.setMessage(tasks.findAll(commandFields));
    }

    @Override
    public void executeAsMassOp(TaskList tasks, UiHandler uiHandler) {
        // TODO: Implement the mass operation
    }
}
