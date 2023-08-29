package rayshawn.chatbot.commands;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Displays all the tasks in the task list with index numbers.\n" +
            "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EMPTY_LIST = "The list is empty. Please add tasks in!";

    @Override
    public CommandResult execute() {
        if (taskList.count() == 0) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
        return new CommandResult(getTaskListCount(), taskList.getAllTasks());
    }
}
