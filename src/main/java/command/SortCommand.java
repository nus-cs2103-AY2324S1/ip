package command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.InvalidCommandException;
import task.Deadline;
import task.Event;

/**
 * SortCommand sorts the Deadline or Event objects specified.
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidCommandException {
        String input = ui.getInput();
        // valid inputs should be: "sort deadlines" and "sort events"
        String taskType = input.split(" ")[1];
        switch (taskType) {
        case "deadlines":
            ArrayList<Deadline> sortedDeadlines = taskList.sortDeadlines();
            String deadlineString = ui.printSortedDeadlines(sortedDeadlines);
            return deadlineString;
        case "events":
            ArrayList<Event> sortedEvents = taskList.sortEvents();
            String eventString = ui.printSortedEvents(sortedEvents);
            return eventString;
        default:
            assert false : taskType;
            throw new InvalidCommandException("Invalid sort command given.\n"
                    + "'sort' command should be followed by 'deadlines' or 'events'");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
