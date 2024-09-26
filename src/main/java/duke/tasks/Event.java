package duke.tasks;

import duke.exceptions.DukeException;

public class Event extends Task{
    private final String[] descriptionArray;

    public Event(String[] descriptionArray) {
        super(descriptionArray[0]);
        this.descriptionArray = descriptionArray;
    }

    /**
     * Checks if the input is valid.
     *
     * @throws DukeException if input is invalid.
     */
    public void checkValidity() throws DukeException {
        if (descriptionArray.length < 3) {
            throw new DukeException("You are missing event details!");
        }
    }

    /**
     * Formats start and end time of event.
     *
     * @param descriptionArray the task description in an array.
     * @return reformatted String version of task.
     */
    public String reformatDetails(String[] descriptionArray) {
        assert descriptionArray[1] != null : "Start time cannot be null";
        assert descriptionArray[2] != null : "End time cannot be null";
        String start = descriptionArray[1];
        String end = descriptionArray[2];
        return " (from: " + start + " to: " + end + ")";
    }

//    public String breakdown(String descr) {
//        String[] descrArr = descr.split("/");
//
//        String start = descrArr[1];
//        String[] parts = start.split(" ");
//        String from = parts[0];
//        String restOfFrom = start.substring(from.length()).trim();
//        assert restOfFrom.length() > 0 : "Invalid start time";
//
//        String end = descrArr[2];
//        String[] parts2 = end.split(" ");
//        String to = parts2[0];
//        String restOfTo = end.substring(to.length()).trim();
//        assert restOfTo.length() > 0 : "Invalid end time";
//
//        return " (from: " + restOfFrom + " to: " + restOfTo + ")";
//    }

    /**
     * Reformats events to be ready to be written into tasks.txt
     *
     * @return the reformatted event,
     * in the format E | 0 | birthday | mon 3pm | mon 5pm
     */

//    public String writtenFormat() {
//        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: start, 2: end
//
//        String start = descrArr[1];
//        String[] parts = start.split(" ");
//        String from = parts[0];
//        String restOfFrom = start.substring(from.length()).trim();
//
//        String end = descrArr[2];
//        String[] parts2 = end.split(" ");
//        String to = parts2[0];
//        String restOfTo = end.substring(to.length()).trim();
//
//        String eventType = "event";
//        String eventDescription = descrArr[0].substring(eventType.length()).trim();
//
//        return "E | " + super.status() + " | " + eventDescription + " | " + restOfFrom + " to " + restOfTo;
//    }

    public String writtenFormat() {
        String eventType = "event";
        String eventDescription = descriptionArray[0].substring(eventType.length()).trim();
        String start = descriptionArray[1];
        String end = descriptionArray[2];
        return "E | " + super.status() + " | " + eventDescription + " | " + start + " to " + end;
    }

    /**
     * Converts task to string.
     *
     * @return formatted String version of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + reformatDetails(this.descriptionArray);
    }
}