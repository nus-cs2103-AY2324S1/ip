package remy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that deletes the given Task from the TaskList upon executing.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    /* Define and compile the regex pattern */
    public static final Pattern PATTERN = Pattern.compile("^delete\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
    private int index;

    /**
     * Creates new Delete command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information.
     */
    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public DeleteCommand(String input) throws ChatbotException {
        Matcher matcher = PATTERN.matcher(input);

        // Wrong input
        if (!matcher.matches()) {
            throw new ChatbotException("missing info lah. You must give a number.");
        }

        // Obtain index from input
        int index = Integer.parseInt(input.substring(7)) - 1;

        if (index < 0) {
            throw new ChatbotException("bro, the number must be larger than 0");
        }

        this.index = index;
    }

    /**
     * Deletes the Task indicated by the index.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     * @throws ChatbotException if given index does not match any Task on the TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        if (this.index >= taskList.size()) {
            throw new ChatbotException("Your task list don't have this number lah.");
        }
        assert this.index > 0;

        String task = taskList.get(this.index).toString();
        taskList.remove(this.index);
        String content = "Done. Can you don't be so troublesome?\n" + task;
        storage.save(taskList);
        return content;
    }
}
