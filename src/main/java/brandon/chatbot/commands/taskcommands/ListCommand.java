package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.LIST_FAIL;
import static brandon.chatbot.commands.Feedback.LIST_SUCCESS;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that lists the tasks in TaskList.
 */
public class ListCommand extends Command {

    @Override
    public CommandResult execute() {
        if (tasks.isEmpty()) {
            return new CommandResult(LIST_FAIL);
        }
        return new CommandResult(LIST_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
