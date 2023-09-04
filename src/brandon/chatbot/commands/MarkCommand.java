package commands;

import common.DukeIndexOutOfBoundsException;

public class MarkCommand extends Command {
    public final String MARK_SUCCESS = "ok... I'm marking...";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException {
        tasks.markAsDone(index);
        return new CommandResult(MARK_SUCCESS);
    }
}
