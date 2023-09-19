package joe.commands;

import java.time.LocalDateTime;

import joe.Storage;
import joe.TaskList;
import joe.exceptions.JoeException;
import joe.tasks.EventTask;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private static final String START_IS_AFTER_END_ERR_MSG = "Please ensure that start datetime is after end datetime";
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
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JoeException {
        int startSize = tasks.size();

        if (to.isBefore(from)) {
            throw new JoeException(START_IS_AFTER_END_ERR_MSG);
        }

        //Ensures that end datetime is at least start datetime
        assert to.isAfter(from) || to.isEqual(from);

        EventTask newTask = new EventTask(taskDetails, from, to);
        tasks.add(newTask);

        //Asserts that one task is added
        int endSize = tasks.size();
        assert endSize - startSize == 1;

        storage.saveToFile(tasks);

        return (
                String.format(
                        "Got it, I've added this task:\n %s\nNow you have %d tasks in the list.",
                        newTask, tasks.size()));
    }
}
