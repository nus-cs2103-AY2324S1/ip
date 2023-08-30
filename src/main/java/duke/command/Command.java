package duke.command;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.DukeException;

public abstract class Command {
    private String type;
    private String description;

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
