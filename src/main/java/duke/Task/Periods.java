package duke.Task;

import duke.DukeException.DukeException;

/**
 * Represents task belong to deadline.
 */
public class Periods extends Task {
    /**
     * Deadline of the task.
     */
    private String start;
    private String end;

    /**
     * Creates the deadline task.
     * @param name Description of the task.
     * @param start start of the task need to be done.
     * @param end end of the task.
     */
    public Periods (String name, String start, String end) throws DukeException {
        super(name);
        this.start = start;
        this.end = end;

    }

    /**
     * Creates the string to be saved in storage.
     * @return String that will be saved in storage.
     */
    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "P,0" + this.getName() + "," + this.start + "," + this.end + "\n";
        } else {
            return "P,1," + this.getName() + "," + this.start + "," + this.end + "\n";
        }
    }

    /**
     * Converts the task to a string.
     * @return String that represent the deadline.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + "(between: "+ this.start + " and " + this.end + ")";
    }

    /**
     * Checks whether the input is a valid deadline.
     * @param input Task that will be checked.
     * @return Boolean that represent whether the input is a deadline.
     * @throws DukeException Exception where the deadline is not valid.
     */
    public static boolean isPeriod(String input) throws DukeException {
        if (input.split(" ")[0].equals("period")) {
            if (!input.contains("/between")) {
                throw new DukeException("OOPS! The description of event does not contain /between.");
            } else if (!input.contains("/and")) {
                throw new DukeException("OOPS! The description of event does not contain /and.");
            } else if (input.split("/and").length == 1) {
                throw new DukeException("OOPS! The description of /and cannot be empty.");
            } else if (input.split("/and")[0].length() - input.split("/between")[0].length() <= 10) {
                throw new DukeException("OOPS! The description of /between cannot be empty.");
            } else if (input.split("/between")[0].length() <= 8) {
                throw new DukeException("OOPS! The description of period cannot be empty.");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}