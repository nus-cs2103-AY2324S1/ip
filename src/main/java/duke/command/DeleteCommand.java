package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A class that represents the user command to delete a task
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * A method that executes the command that user gave
     * @params tasks TaskList containing all existing Task objects
     * @params ui UI interface that is used to print messages to the terminal
     * @params storage Storage object that houses database of the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.deleteTask(tasks, index);
        tasks.delete(index);
        storage.update(tasks);
    }

}
