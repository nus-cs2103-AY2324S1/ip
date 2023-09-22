package duke.command;

import duke.tasklist.TaskList;

public class PrintEventsCommand extends Command {

    private static final String COMMAND_RESPONSE = "You have %d events on your list!";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks.getEvents());
    }

    private String response(TaskList events) {
        return String.format(COMMAND_RESPONSE, events.getSize()) + events.getFormattedList();
    }
}
