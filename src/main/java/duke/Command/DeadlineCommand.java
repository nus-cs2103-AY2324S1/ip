package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;


public class DeadlineCommand extends Command {
    private String description;
    private String by;
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws IOException, DukeException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
