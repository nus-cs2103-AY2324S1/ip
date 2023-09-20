package commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        return ui.bye();
    }
}
