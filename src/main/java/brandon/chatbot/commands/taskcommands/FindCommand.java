package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

/**
 * Represents a command that finds a task in the task list with a given keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_SUCCESS = "ok... I'm finding the task...";
    private String title;

    public FindCommand(String title) {
        this.title = title;
    }
    @Override
    public CommandResult execute() {
        assert this.title != null: "Title of the task should not be empty here.";

        TaskList newList = new TaskList();
        for (Task task : tasks.getList()) {
            if (task.toString().contains(this.title)) {
                newList.addTask(task);
            }
        }

        return new CommandResult(FIND_SUCCESS, newList);
    }
}
