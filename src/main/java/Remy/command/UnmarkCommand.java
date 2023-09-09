package remy.command;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that marks a given Task as undone upon executing.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;

    /**
     * Creates new Unmark Command that parses user input to identify the Task to be marked as completed.
     *
     * @param input Input submitted by the user.
     * @throws ChatbotException if input is in the wrong format or has missing information.
     */
    public UnmarkCommand(String input) throws ChatbotException {
        if (input.length() < 8) {
            throw new ChatbotException("missing info lah.");
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0) {
            this.index = index;
        }

    }

    /**
     * Marks the previously indicated Task as incomplete.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @return message showing successful execution.
     * @throws ChatbotException if given index does not match any Task on the TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {

        if (this.index >= taskList.size()) {
            throw new ChatbotException("No such item lah.");
        }
        taskList.get(index).markAsUndone();
        String content = "Done. You happy?\n" + taskList.get(index).toString();
        storage.save(taskList);
        return content;
    }
}
