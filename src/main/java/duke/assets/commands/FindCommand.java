package duke.assets.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.storage.TaskList;

/**
 * Command to find the tasks with description matching a user input token
 */
public class FindCommand extends CommandAbstract {
    /**
     * Constructs a find command
     * @param input input command from user
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Print the chatbot dialogue invoked from this command
     */
    @Override
    public void printChatbotLine() { // Printing is done by task list instead
        return;
    }

    /**
     * Checks if the command is of appropriate formatting
     * @param tasklist the task list to find tokens in
     * @return true if command is of appropriate formatting, false otherwise
     */
    @Override
    protected boolean isValid(TaskList tasklist) {
        Pattern commandRegex = Pattern.compile("^find .+", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    /**
     * Completes the search operation
     * @param tasklist the task list to search for the specific token in
     */
    @Override
    protected void completeOperation(TaskList tasklist) {
        String token = this.input.split("^((?i)(find))\\s")[1];
        tasklist.find(token);
    }
}
