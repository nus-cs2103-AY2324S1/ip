package brandon.chatbot.commands.taskcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.common.DukeIndexOutOfBoundsException;

/**
 * Represents a deleting task command the user typed in.
 */
public class DeleteCommand extends Command {
    public static final String DELETE_SUCCESS = "ok... I'm deleting...-ㅅ-";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException {
        tasks.deleteTask(index);
        return new CommandResult(DELETE_SUCCESS);
    }
}
