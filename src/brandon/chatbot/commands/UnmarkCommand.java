package commands;

import common.DukeIndexOutOfBoundsException;

import java.io.IOException;

public class UnmarkCommand extends Command {
    public final String UNMARK_SUCCESS = "ok... I'm unmarking...";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException, IOException {
        tasks.unmark(index);
        return new CommandResult(UNMARK_SUCCESS);
    }
}
