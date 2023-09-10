package commands;

import tasks.TaskList;

/**
 * This child class instructs the application to create a Deadline object
 * to add to the task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String endTime;

    /**
     * Constructs a DeadlineCommand object.
     *
     * @param description
     * @param endTime
     */
    public DeadlineCommand(String description, String endTime) {
        this.description = description;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addDeadlineTask(description, endTime);
    }
}
