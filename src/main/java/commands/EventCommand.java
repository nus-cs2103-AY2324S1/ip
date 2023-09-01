package commands;

import tasks.TaskList;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private String description;
    private String start;
    private String end;

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
