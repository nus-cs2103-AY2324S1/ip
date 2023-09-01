package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.exceptions.DukeException;
import duke.utils.Ui;

public class FindCommand extends Command {
    
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.print(tasklist.find(description));
    }
}
