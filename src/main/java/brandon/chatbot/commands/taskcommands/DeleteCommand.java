package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.DELETE_SUCCESS;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeIndexOutOfBoundsException;

/**
 * Represents a deleting task command the user typed in.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException {
        tasks.deleteTask(index);
        return new CommandResult(DELETE_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
