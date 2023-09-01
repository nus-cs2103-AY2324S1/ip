package duke.tasks;

import duke.exceptions.DukeException;

public class Event extends Task{
    private final String descr;

    public Event(String descr) {
        super(descr.split("/")[0]);
        this.descr = descr;
    }

    /**
     * Method that checks if the input is valid.
     *
     * @throws DukeException if input is invalid.
     */
    public void checkValidity() throws DukeException {
        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: start, 2: end
        if (descrArr.length < 3) {
            throw new DukeException("You are missing event start and/or end details");
        }
    }

    /**
     * Method to format input.
     *
     * @param descr the task description.
     * @return reformatted String version of task.
     */
    public String breakdown(String descr) {
        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: start, 2: end

        String start = descrArr[1];
        String[] parts = start.split(" ");
        String from = parts[0];
        String restOfFrom = start.substring(from.length()).trim();

        String end = descrArr[2];
        String[] parts2 = end.split(" ");
        String to = parts2[0];
        String restOfTo = end.substring(to.length()).trim();

        return " (from: " + restOfFrom + " to: " + restOfTo + ")";
    }

    /**
     * Method that reformats events to be ready to be written into tasks.txt.
     *
     * @return the reformatted event.
     */

    public String writtenFormat() {
        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: start, 2: end

        String start = descrArr[1];
        String[] parts = start.split(" ");
        String from = parts[0];
        String restOfFrom = start.substring(from.length()).trim();

        String end = descrArr[2];
        String[] parts2 = end.split(" ");
        String to = parts2[0];
        String restOfTo = end.substring(to.length()).trim();

        String eventType = "event";
        String eventDescription = descrArr[0].substring(eventType.length()).trim();

        return "E | " + super.status() + "| " + eventDescription + " | " + restOfFrom + " to " + restOfTo;
    }

    /**
     * Method that converts task to string.
     *
     * @return formatted String version of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + breakdown(this.descr);
    }
}
//project meeting /from Mon 2pm /to 4pm