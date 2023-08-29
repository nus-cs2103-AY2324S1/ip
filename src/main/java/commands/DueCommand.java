package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.UI;

import java.io.IOException;
import java.time.LocalDate;

public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    private final LocalDate date;
    public DueCommand(LocalDate date) {
        this.date = date;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        ui.showTasksDueOn(date, taskList.showTasksDueOn(date));
    }
}
