package duke.assets.commands;

import duke.assets.storage.TaskList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a command to mark a task as done in the task list
 */
public class MarkCommand extends OperationOnListCommandAbstract {

    /**
     * Constructs a new MarkCommand object with the given input command string
     *
     * @param input the input command string
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Completes the operation specified by the input command on the specified task list
     *
     * @param tasklist the task list to operate on
     */
    @Override
    protected void completeOperation(TaskList tasklist) {
        tasklist.markTaskAt(Integer.parseInt(input.split(" ")[1]) - 1);
    }
}