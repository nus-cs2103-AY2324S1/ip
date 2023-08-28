package rayshawn.chatbot.commands;

import rayshawn.chatbot.tasks.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Use to mark a task as completed\n" +
            "Parameters: INDEX\n" +
            "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done: %1$s";

    public MarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        final Task task = taskList.getTask(getIndex() - 1);
        task.markDone();
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
