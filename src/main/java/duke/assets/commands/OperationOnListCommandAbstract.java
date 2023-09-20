package duke.assets.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.storage.TaskList;

/**
 * An abstract command class that is parent of all possible commands that operates on the task list for the chatbot
 */
public abstract class OperationOnListCommandAbstract extends CommandAbstract {

    /**
     * Constructs a new OperationOnListCommandAbstract object with the given input command string
     *
     * @param input the input command string
     */
    public OperationOnListCommandAbstract(String input) {
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
        Pattern commandRegex = Pattern.compile("^(mark|unmark|delete)\\s\\d+$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        return inputMatcher.find();
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param taskList the task list to operate on
     * @return string of appropriate bot response, or UNHANDLED_EXCEPTION_STRING for any unhandled edge cases
     */
    @Override
    protected abstract String completeOperation(TaskList taskList);

    /**
     * Handles exceptions that occur when validating the input command and returns the appropriate chatbot
     * response as a string
     *
     * @return string of appropriate bot response, or UNHANDLED_EXCEPTION_STRING for any unhandled edge cases
     */
    @Override
    protected String findException() {
        Pattern commandRegex = Pattern.compile("^(mark|unmark|delete)\\s\\d+$", Pattern.CASE_INSENSITIVE);
        Matcher inputMatcher = commandRegex.matcher(this.input);
        if (!inputMatcher.find()) {
            inputMatcher.reset();
            Pattern inputStartRegex = Pattern.compile("^(mark|unmark|delete)\\s", Pattern.CASE_INSENSITIVE);
            if (inputMatcher.usePattern(inputStartRegex).find()) {
                return "Ensure that you have included the index value of the task you would like to"
                        + "alter";
            }
        }
        return UNHANDLED_EXCEPTION_STRING;
    }
}
