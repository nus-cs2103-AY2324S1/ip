package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    private String ErrMessage;

    public IncorrectCommand(String ErrMessage) {
        this.ErrMessage = ErrMessage;
    }

    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(this.ErrMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
