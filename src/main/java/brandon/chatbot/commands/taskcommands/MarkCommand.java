package brandon.chatbot.commands.taskcommands;

import static brandon.chatbot.commands.Feedback.MARK_SUCCESS;

import brandon.chatbot.Message;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeIndexOutOfBoundsException;

/**
 * Represents a command that marks the finished status of a task in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException {
        tasks.markAsDone(index);
        return new CommandResult(MARK_SUCCESS + "\n" + Message.showTasks(tasks));
    }
}
