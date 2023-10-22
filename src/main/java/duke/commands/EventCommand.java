package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.TaskList;

import java.util.Date;

/**
 * Represents and Event Command to be executed
 */
public class EventCommand extends Command {
    private String input;

    public EventCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * create event object, splitting the due date by "/" and stripping off the to: and from:
     *
     * @return
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "event");
        int startIndex = input.indexOf("/from ");
        int endIndex = input.indexOf("/to");

        Date from = dateParser(input.substring(startIndex + 6, endIndex - 1));
        Date to = dateParser(input.substring(endIndex + 4));

        Event e = new Event(input.substring(6, startIndex), from, to);
        taskList.addTask(e);
        return e.addedMessage();
    }
}
