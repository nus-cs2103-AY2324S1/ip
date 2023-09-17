package slay.command;

import slay.Message;
import slay.exception.DuplicatedMarkException;
import slay.exception.InvalidTaskIndexException;
import slay.task.*;

/**
 * Tag a tong.task.Task with labels.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    /** The Index representing the task to be tagged which is visible to the users (starting from 1) */
    private int targetVisibleIndex = 0;
    private String label;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tag the task identified by the index number with the given label.\n"
            + "Parameters: INDEX LABEL\n"
            + "Example: " + COMMAND_WORD + " 1" + " CS\n";

    public static final String MESSAGE_TAG_TASK_WITHOUT_EXISTING_TAG_SUCCESS =
            "You have tagged the task: %1$s";
    public static final String MESSAGE_TAG_TASK_WITH_EXISTING_TAG_SUCCESS =
            "You have changed the tag the task: %1$s\n"
            + "(Previous Tag: #%2$s)";

    public TagCommand(int targetVisibleIndex, String label) {
        this.targetVisibleIndex = targetVisibleIndex;
        this.label = label;
    }

    @Override
    public CommandResult execute() {
        try {
            Task toTag = taskList.getTask(targetVisibleIndex);
            String existingTag = toTag.getTag();
            taskList.tagTask(toTag, this.label);

            if (existingTag == null) {
                return new CommandResult(String.format(MESSAGE_TAG_TASK_WITHOUT_EXISTING_TAG_SUCCESS, toTag));
            } else {
                return new CommandResult(String.format(MESSAGE_TAG_TASK_WITH_EXISTING_TAG_SUCCESS, toTag, existingTag));
            }
        }  catch (InvalidTaskIndexException e) {
            return new CommandResult(Message.MESSAGE_INVALID_TASK_INDEX);
        }
    }
}
