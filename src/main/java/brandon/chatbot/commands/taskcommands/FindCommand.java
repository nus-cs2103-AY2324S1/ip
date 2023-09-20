package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.tag.Tag;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a command that finds a task in the task list with a given keyword.
 */
public class FindCommand extends Command {
    private static final String FIND_SUCCESS = "ok... I'm finding the task...\n";
    private String title;

    public FindCommand(String title) {
        this.title = title;
    }

    @Override
    public CommandResult execute() {
        TaskList newList = new TaskList();
        for (Task t: tasks.getList()) {
            if (t.toString().contains(title)) {
                newList.addTask(t);
            }
        }

        return new CommandResult(FIND_SUCCESS + Message.showTasks(newList));
    }
}
