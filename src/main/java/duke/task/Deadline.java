package duke.task;

import duke.exception.DukeDateOutOfRange;
import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.processors.TimeProcessor;

/**
 * Deadline contains a fixed date by which a task should be completed.
 */
public class Deadline extends Task{

    /**
     * Constructor for deadline.
     *
     * @param Description The information needed to create a deadline
     * @throws DukeNoDescriptionException if the description is empty
     *                                    despite "deadline"
     * @throws DukeNoDateException if the date is missing
     * @throws DukeDateOutOfRange if the date is not a proper date
     */
    public Deadline(String Description) throws DukeNoDescriptionException,
            DukeNoDateException, DukeDateOutOfRange{
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Deadline");
        }
        int index = Description.indexOf("/");
        if (index == -1) {
            throw new DukeNoDateException("Deadline");
        }
        String content = Description.substring(9, index);
        String subString = Description.substring(index + 4);
        String time;
        if (subString.contains(" ")) {
            int indexOfSpace = subString.indexOf(" ");
            time = TimeProcessor.StringToDate(subString.substring(0, indexOfSpace));
            time = time + subString.substring(indexOfSpace);
        } else {
            time = TimeProcessor.StringToDate(Description.substring(index + 4));
        }
        time = Description.substring(index + 1, index + 3) + ": " + time;
        this.Description = content + "(" + time + ")";
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    /**
     * Another constructor for deadline.
     * @param content The description for deadline
     * @param isDone The status of the deadline.
     */
    public Deadline(String content, boolean isDone) {
        super(content);
        this.isDone = isDone;
    }

    /**
     * A string contains the status, the label
     * and description of deadline.
     * @return A string containing info of the deadline
     */
    public String toString() {

        return "[D]" + super.toString();
    }
}
