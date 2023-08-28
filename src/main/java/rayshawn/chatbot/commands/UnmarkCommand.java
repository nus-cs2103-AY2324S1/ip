package rayshawn.chatbot.commands;

import rayshawn.chatbot.tasks.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Use to unmark a task as not completed\n" +
            "Parameters: INDEX\n" +
            "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task not done yet: %1$s";

    public UnmarkCommand(int index) {
        super(index);
    }

    @Override
    public CommandResult execute() {
        final Task task = taskList.getTask(getIndex() - 1);
        task.unmarkDone();
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
