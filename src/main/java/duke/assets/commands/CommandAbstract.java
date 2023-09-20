package duke.assets.commands;

import duke.assets.storage.TaskList;

/**
 * An abstract command class that is parent of all possible commands for the chatbot
 */
public abstract class CommandAbstract {
    // Constants
    protected static final String HORIZONTAL = "------------------------------------------------------------"
            + "---------------------------";
    protected static final String VALID_DATE_REGEX_STRING = " (\\d{4}/\\d{2}/\\d{2}|\\d{4}-\\d{2}-\\d{2})( |$)";
    protected static final String VALID_TIME_REGEX_STRING = " [0-2][0-9][0-5][0-9]($| )";
    protected static final String UNHANDLED_EXCEPTION_STRING = "Error: unexpected uncaught exception in command";
    // Non-Constants
    protected String input;

    /**
     * Constructs a new CommandAbstract object with the given input command string
     *
     * @param input the input command string
     */
    public CommandAbstract(String input) {
        this.input = input;
    }

    /**
     * Executes the given input command on the specified task list
     *
     * @param tasklist the task list to operate on
     * @return appropriate chatbot response to user query
     */
    public String execute(TaskList tasklist) {
        if (isValid(tasklist)) {
            return completeOperation(tasklist);
        } else {
            return findException();
        }
    }

    /**
     * Determines whether the input command is valid for the specified task list
     *
     * @param tasklist the task list to validate against
     * @return true if the input command is valid, false otherwise
     */
    protected abstract boolean isValid(TaskList tasklist);

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param tasklist the task list to operate on
     * @return the appropriate chatbot response to the user query, or UNHANDLED_STRING_EXCEPTION for unhandled
     *     edge cases
     */
    protected abstract String completeOperation(TaskList tasklist);

    /**
     * Handles exceptions that occur when validating the input command and returns the appropriate chatbot
     * response as a string
     *
     * @return string of the appropriate bot response, or UNHANDLED_STRING_EXCEPTION for unhandled edge cases
     */
    protected abstract String findException();
}
