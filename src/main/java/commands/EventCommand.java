package commands;

import tasks.TaskList;

/**
 * This child class instructs the application to create an Event object
 * to add to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private String description;
    private String start;
    private String end;

    /**
     * Constructs an EventCommand object.
     *
     * @param description
     * @param start
     * @param end
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addEventTask(description, start, end);
    }
}
