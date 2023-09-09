package remy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import remy.ChatbotException;
import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that finds matching Tasks upon executing.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    // Regex pattern for "find {keyword}" format
    private static final Pattern FIND_PATTERN = Pattern.compile("^find\\s+(\\S+)$", Pattern.CASE_INSENSITIVE);
    private String searchString;

    /**
     * Constructs a new FindCommand if input is of the correct format.
     * @param input Command typed by the user.
     * @throws ChatbotException if input is in the wrong format or is missing information.
     */
    public FindCommand(String input) throws ChatbotException {
        Matcher matcher = FIND_PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.searchString = matcher.group(1).toLowerCase();
        } else {
            throw new ChatbotException("format wrong bro");
        }
    }

    /**
     * Prints out the list of matching Tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        String searchResults = taskList.findMatchingTasks(this.searchString);
        return "Here are the matching tasks in your list: \n" + searchResults;
    }
}
