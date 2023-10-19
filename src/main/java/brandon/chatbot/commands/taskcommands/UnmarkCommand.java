package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.UNMARK_SUCCESS;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeIndexOutOfBoundsException;

/**
 * Represents the command that unmarks the finished status of a task.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException {
        tasks.unmark(index);
        return new CommandResult(UNMARK_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
