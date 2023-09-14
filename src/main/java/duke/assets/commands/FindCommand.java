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
     *
     * @param input input command from user
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Checks if the command is of appropriate formatting
     *
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
     *
     * @param tasklist the task list to search for the specific token in
     * @return string of appropriate bot response
     */
    @Override
    protected String completeOperation(TaskList tasklist) {
        String token = this.input.split("^((?i)(find))\\s")[1];
        return tasklist.find(token);
    }

    /**
     * Unused method as command has no possible exceptions that are not already caught by the task list in the
     * completeOperation method
     *
     * @return UNHANDLED_EXCEPTION_STRING if there are any edge cases not considered
     */
    @Override
    protected String findException() {
        assert(false);
        return UNHANDLED_EXCEPTION_STRING;
    }
}
