package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.tasks.EventTask;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskDetails;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String taskDetails, LocalDateTime from, LocalDateTime to) {
        this.taskDetails = taskDetails;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        EventTask newTask = new EventTask(taskDetails, from, to);
        tasks.add(newTask);

        ui.print(
                String.format(
                        "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.",
                        newTask, tasks.size()));

        storage.saveToFile(tasks);
    }
}
