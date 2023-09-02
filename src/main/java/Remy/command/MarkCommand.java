package Remy.command;

import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarkCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "mark";

    // Modified from addressBook Level 2
    // Source: https://github.com/se-edu/addressbook-level2
    public MarkCommand(String input) throws ChatbotException {

        // Define and compile the regex pattern
        Pattern pattern = Pattern.compile("^mark\\s+(\\d+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new ChatbotException("missing info lah.");
        }
        int index = Integer.parseInt(matcher.group(1));
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
            throw new ChatbotException("Your Task list don't have this number lah.");
        }
        taskList.get(index).markAsDone();
        String content = "Done. You happy?\n" + taskList.get(index).toString();
        storage.save(taskList);
        Ui.printShortSandwich(content);
    }

}
