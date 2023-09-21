package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.TaskList;

public class EventCommand extends Command {

    /**
     * Adds new event to task list.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return success message upon successful adding of event.
     * @throws DukeException when no description or no start time
     * or no end time is provided for the event.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("Please provide a description for this deadline! (⋟﹏⋞)");
        } else {
            message = addEvent(parsedInput[1], message);
        }

        return message;
    }

    public String addEvent(String details, String msg) throws DukeException{
        String[] eventTask = details.split("/from");
        if (eventTask.length == 1) {
            throw new DukeException("Please provide a start time! (⋟﹏⋞)");
        } else if (eventTask[0].equals("")) {
            throw new DukeException("Please provide a description! (⋟﹏⋞)");
        } else {
            String eventDescription = eventTask[0];
            String[] startEnd = eventTask[1].split("/to");
            if (startEnd.length == 1) {
                throw new DukeException("Please provide an end time! (⋟﹏⋞)");
            } else {
                String eventStart = startEnd[0];
                String eventEnd = startEnd[1];
                Event newEvent = new Event(eventDescription, eventStart, eventEnd);
                msg = TaskList.add(newEvent, "event");
                assert msg != null : "response should be given";
            }
        }

        return msg;
    }
}
