package rayshawn.chatbot.commands;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Displays all the tasks in the task list with index numbers.\n" +
            "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(getTaskListCount(), taskList.getAllTasks());
    }
}
