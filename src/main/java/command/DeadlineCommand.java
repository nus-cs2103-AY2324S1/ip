package command;

import java.time.LocalDateTime;

import duke.Storage;
import task.Deadline;
import task.TaskList;

/**
 * Adds a deadline, which has a description and a date/time to do by, to the todo list
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";

    /** Description of the task */
    protected String description;

    /** Deadline that the task is to be done by */
    protected LocalDateTime by;

    /**
     * Creates an add deadline command with the given description and date/time the task is to be done by
     *
     * @param description description of the task
     * @param by date/time the task is to be done by
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds the deadline task to the given TaskList, and saves the current TaskList in
     * the specified Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        int originalSize = tasks.getSize();
        tasks.addTask(deadline);
        assert tasks.getSize() == originalSize + 1 : "Task was not properly added to the list";
        storage.writeToFile(tasks.getList());
        String response = MESSAGE_SUCCESS + deadline
                + "\nNow you have " + tasks.getSize() + " tasks in the list";
        return response;
    }
}
