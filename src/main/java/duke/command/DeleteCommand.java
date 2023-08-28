package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);
        ui.deleteTask(tasks, index);
        storage.update(tasks);
    }

}
