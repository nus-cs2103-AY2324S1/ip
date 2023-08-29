package rayshawn.chatbot.commands;

import rayshawn.chatbot.messages.Messages;
import rayshawn.chatbot.tasks.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Use to unmark a task as not completed\n" +
            "Parameters: INDEX\n" +
            "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task not done yet: \n %1$s";
    public static final String MESSAGE_REPEATED = "This task is not done yet!";

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task task = taskList.getTask(getIndex() - 1);
            if (task.checkDone()) {
                task.unmarkDone();
                return new CommandResult(String.format(MESSAGE_SUCCESS, task));
            } else {
                return new CommandResult(MESSAGE_REPEATED);
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.INVALID_TASK_NUMBER_MESSAGE);
        }

    }
}
