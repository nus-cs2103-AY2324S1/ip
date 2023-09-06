package brandon.chatbot.commands;

import brandon.chatbot.common.DukeIndexOutOfBoundsException;

public class DeleteCommand extends Command{
    public final String DELETE_SUCCESS = "ok... I'm deleting...";
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
