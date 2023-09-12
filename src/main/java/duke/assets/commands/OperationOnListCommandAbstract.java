package duke.assets.commands;

import duke.assets.storage.TaskList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
        if (!inputMatcher.find()) {
            inputMatcher.reset();
            Pattern inputStartRegex = Pattern.compile("^(mark|unmark|delete)\\s", Pattern.CASE_INSENSITIVE);
            if (inputMatcher.usePattern(inputStartRegex).find()) {
                System.out.println("Ensure that you have included the index value of the task you would like to" +
                        "alter");
            }
            return false;
        }
        return true;
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param taskList the task list to operate on
     */
    @Override
    protected abstract void completeOperation(TaskList taskList);

    /**
     * Prints the appropriate dialogue from the chatbot to the terminal
     */
    @Override
    public void printChatbotLine() {
        return;
    }
}