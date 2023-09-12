package duke.assets.commands;

import duke.assets.storage.TaskList;
import duke.dukeexceptions.InvalidCommandException;

/**
 * An abstract command class that is parent of all possible commands for the chatbot
 */
public abstract class CommandAbstract {

    /**
     * A horizontal line used for formatting output
     */
    public static String HORIZONTAL = "------------------------------------------------------------" +
            "---------------------------";

    /**
     * A regular expression for validating dates in the format yyyy/MM/dd or yyyy-MM-dd
     */
    protected static final String VALID_DATE_REGEX_STRING = "(\\d{4}/\\d{2}/\\d{2}|\\d{4}-\\d{2}-\\d{2})";

    /**
     * A regular expression for validating times in the format HHmm
     */
    protected static final String VALID_TIME_REGEX_STRING = "[0-2][0-9][0-5][0-9]";

    /**
     * The input command string
     */
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
     * @throws InvalidCommandException if the input command has an invalid format
     */
    public void execute(TaskList tasklist) throws InvalidCommandException {
        if (isValid(tasklist)) {
            completeOperation(tasklist);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Prints the appropriate dialogue from the chatbot to the terminal
     */
    public abstract void printChatbotLine();

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
     */
    protected abstract void completeOperation(TaskList tasklist);
}