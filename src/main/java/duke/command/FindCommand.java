package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyString;

    public FindCommand(String keyString) {
        this.keyString = keyString;
    }

    @Override 
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.find(tasks, this.keyString);
    }
}
