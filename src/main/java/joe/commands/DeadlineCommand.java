package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.tasks.DeadlineTask;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String taskDetails;
    private final LocalDateTime by;

    public DeadlineCommand(String taskDetails, LocalDateTime by) {
        this.taskDetails = taskDetails;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        DeadlineTask newTask = new DeadlineTask(taskDetails, by);
        tasks.add(newTask);

        ui.print(
                String.format(
                        "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.",
                        newTask, tasks.size()));

        storage.saveToFile(tasks);
    }
}
