package rayshawn.chatbot.commands;

import rayshawn.chatbot.messages.Messages;
import rayshawn.chatbot.tasks.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Deletes the task identified by the index number used shown in the task listing. \n" +
            "Parameters: INDEX\n" +
            "Example: " + COMMAND_WORD + " 1";
    private static final String MESSAGE_SUCCESS = "Noted. I've removed this task: \n %1$s";

    public DeleteCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        try {
            final Task target = super.getTask(super.getIndex() - 1);
            taskList.removeTask(super.getIndex());
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.INVALID_TASK_NUMBER_MESSAGE);
        }
    }
}
