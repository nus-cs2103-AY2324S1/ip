package duke.commands;

import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.addTask(this.task);

        storage.save(tasks);

        String format = "Got it. I've added this task:\n%s\n" + "Now you have %d tasks in the list.";
        String message = String.format(format, this.task, tasks.getNumberOfTasks());

        ui.appendResponse(message);
    }
}
