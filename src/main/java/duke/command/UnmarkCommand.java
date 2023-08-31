package duke.command;

import duke.*;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskNumber; // the number of the task to be marked

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            Task task = tasks.get(taskNumber - 1); // arrays are 0-based, so subtract 1
            task.unmarkAsDone();
            storage.saveTasks(tasks); // Save the updated tasks to file
            ui.showTaskUnmarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number.");
        }
    }
}
