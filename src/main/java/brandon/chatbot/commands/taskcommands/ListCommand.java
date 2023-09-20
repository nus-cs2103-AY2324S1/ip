package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that lists the tasks in TaskList.
 */
public class ListCommand extends Command {
    public static final String LIST_SUCCESS = "ok... I'm listing..-ㅅ-";
    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
