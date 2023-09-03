package Remy.command;
import Remy.ChatbotException;
import Remy.Storage;
import Remy.Task.TaskList;
import Remy.Ui;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Command that finds matching Tasks upon executing.
 */
public class FindCommand extends Command {
    private String searchString;
    public static final String COMMAND_WORD = "find";

    // Regex pattern for "find {keyword}" format
    private static final Pattern FIND_PATTERN = Pattern.compile("^find\\s+(\\S+)$", Pattern.CASE_INSENSITIVE);

    public FindCommand(String input) throws ChatbotException {
        Matcher matcher = FIND_PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.searchString = matcher.group(1).toLowerCase();
        } else {
            throw new ChatbotException("format wrong bro");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out the list of matching Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Ui.printSearchResults(taskList.findMatchingTasks(this.searchString));
    }
}
