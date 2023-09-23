package remy.command;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that deletes the given Task from the TaskList upon executing.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Creates new Delete command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information.
     */
    public DeleteCommand(String input) throws ChatbotException {
        if (input.length() < 8) {
            throw new ChatbotException("missing info lah. Use delete X, where X is a number >0.");
        }
        int index = Integer.parseInt(input.substring(7)) - 1;

        if (index < 0) {
            throw new ChatbotException("bro, the number must be larger than 0");
        }

        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        if (this.index > taskList.size()) {
            throw new ChatbotException("Your Task list don't have this number lah.");
        }
        assert this.index > 0;

        String task = taskList.get(this.index).toString();
        taskList.remove(this.index);
        String content = "Done. Can you don't be so troublesome?\n" + task;
        storage.save(taskList);
        return content;
    }
}
