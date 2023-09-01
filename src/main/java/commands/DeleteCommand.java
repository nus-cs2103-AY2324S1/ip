package commands;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private int taskCount;

    public DeleteCommand(String taskCount) throws DukeException {
        try {
            this.taskCount = Integer.parseInt(taskCount);
        } catch (NumberFormatException e) {
            throw new DukeException(new String[] {
                Ui.cTxt("delete", Ui.Color.PURPLE)
                        + " takes in a number. Try delete 1."
            });
        }
    }

    @Override
    public void execute(
            TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task removedTask = tasks.delete(taskCount - 1);
        ui.displayMsg(new String[] {
            "Okie! I've deleted task " + taskCount + ":",
            "  " + removedTask.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });

        // Write modified task list to file
        storage.update(tasks);
    }
}
