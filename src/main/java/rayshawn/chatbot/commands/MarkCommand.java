package rayshawn.chatbot.commands;

import rayshawn.chatbot.messages.Messages;
import rayshawn.chatbot.tasks.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Use to mark a task as completed\n" +
            "Parameters: INDEX\n" +
            "Example: " + COMMAND_WORD + " 1";
    private static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done: \n %1$s";
    private static final String MESSAGE_REPEATED = "This task is already marked done!";

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task task = taskList.getTask(getIndex() - 1);
            if (task.checkDone()) {
                return new CommandResult(MESSAGE_REPEATED);
            }
            task.markDone();
            return new CommandResult(String.format(MESSAGE_SUCCESS, task));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.INVALID_TASK_NUMBER_MESSAGE);
        }
    }
}
