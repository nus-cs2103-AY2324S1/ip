package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.tasks.EventTask;

import java.time.LocalDateTime;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private final String taskDetails;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventCommand with task details, start time, and end time.
     *
     * @param taskDetails The description of the event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public EventCommand(String taskDetails, LocalDateTime from, LocalDateTime to) {
        this.taskDetails = taskDetails;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage for saving and loading tasks.
     */
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
