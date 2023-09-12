package duke.assets.commands;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import duke.assets.storage.TaskList;

public class FindCommand extends CommandAbstract {
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void printChatbotLine() { // Printing is done by task list instead
        return;
    }

    @Override
    protected boolean isValid(TaskList tasklist) {
        Pattern commandRegex = Pattern.compile("^find .+", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    @Override
    protected void completeOperation(TaskList tasklist) {
        String token = this.input.split("^((?i)(find))\\s")[1];
        tasklist.find(token);
    }
}
