package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.tasks.Task;
import brandon.chatbot.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command that lists the tasks in TaskList.
 */
public class ListCommand extends Command {
    public static final String LIST_SUCCESS = "ok... I'm listing..";
    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
