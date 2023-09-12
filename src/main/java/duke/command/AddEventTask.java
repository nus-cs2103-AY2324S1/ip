package duke.command;

import java.time.LocalDate;

import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.parser.DateParser;


/** Abstraction of a command to add an event task to a list. */
public class AddEventTask extends AddTask {

    /**
     * Creates a command to add an event task to a list.
     *
     * @param list List that event task to be added.
     * @param specifications Description of the event task.
     */
    public AddEventTask(TaskList list, String specifications) {
        super(list, specifications);
    }

    @Override
    public String execute() {
        try {
            String[] split = specifications.split("/from", 2);
            String event = split[0];
            String[] timings = split[1].split("/to", 2);
            LocalDate start = DateParser.parseDate(timings[0]);
            LocalDate end = DateParser.parseDate(timings[1]);
            Task eventTask = new Event(event, false, start, end);
            this.list.store(eventTask);
            return this.ui.showTaskAdded(eventTask, this.list.length());
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new IllegalArgumentException(
                    "OOPS!!! The description of an event must have <task> /from <start> /to <end>.");
        }
    }
}
