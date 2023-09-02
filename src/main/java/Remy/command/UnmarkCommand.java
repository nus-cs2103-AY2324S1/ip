package Remy.command;

import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

/**
 * A Command that marks a given Task as undone upon executing.
 */
public class UnmarkCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "unmark";

    /**
     * Creates new Unmark Command that parses user input to identify the Task to be marked as completed.
     * @param input Input submitted by the user.
     * @throws ChatbotException if input is in the wrong format or has missing information.
     */
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

    /**
     * Marks the previously indicated Task as incomplete.
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException if given index does not match any Task on the TaskList.
     */
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
