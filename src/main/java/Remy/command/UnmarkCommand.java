package Remy.command;

import Remy.task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

public class UnmarkCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "unmark";

    public UnmarkCommand(String input) throws ChatbotException {
        if (input.length() < 8) throw new ChatbotException("missing info lah.");
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0) {
            this.index = index;
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        if (this.index >= taskList.size()) {
            throw new ChatbotException("No such item lah.");
        }
        taskList.get(index).markAsUndone();
        String content = "Done. You happy?\n" + taskList.get(index).toString();
        storage.save(taskList);
        Ui.printShortSandwich(content);
    }
}
