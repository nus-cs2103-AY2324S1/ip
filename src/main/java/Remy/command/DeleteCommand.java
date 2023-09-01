package Remy.command;

import Remy.task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

public class DeleteCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String input) throws ChatbotException {
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
        String task = taskList.get(this.index).toString();
        taskList.remove(this.index);
        String content = "Done. Can you don't be so troublesome?\n" + task;
        storage.save(taskList);
        Ui.printShortSandwich(content);
    }
}
