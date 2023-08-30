package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {

    public AddCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public void execute() {
        System.out.println("Added a task to the list.");
    }

}
