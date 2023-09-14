package duke.assets.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.storage.TaskList;

/**
 * Represents a command to list all tasks in the task list
 */
public class ListCommand extends CommandAbstract {

    /**
     * Constructs a new ListCommand object with the given input command string
     *
     * @param input the input command string
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Determines whether the input command is valid for the specified task list
     *
     * @param tasklist the task list to validate against
     * @return true if the input command is valid, false otherwise
     */
    @Override
    protected boolean isValid(TaskList tasklist) {
        return this.isValid();
    }

    /**
     * Determines whether the input command is valid
     *
     * @return true if the input command is valid, false otherwise
     */
    private boolean isValid() {
        Pattern commandRegex = Pattern.compile("^list$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param taskList the task list to operate on
     * @return appropriate bot response string
     */
    @Override
    protected String completeOperation(TaskList taskList) {
        return taskList.listTasks();
    }

    /**
     * Handles exceptions that occur when validating the input command and returns the appropriate chatbot
     * response as a string
     *
     * @return string of appropriate bot response, or UNHANDLED_EXCEPTION_STRING for any unhandled edge cases
     */
    @Override
    protected String findException() {
        if (this.input.startsWith("list")) {
            return "If you want to list the tasks, please just type the command \"list\"";
        }
        return UNHANDLED_EXCEPTION_STRING;
    }
}
