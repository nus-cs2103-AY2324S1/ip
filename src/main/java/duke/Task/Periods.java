package duke.Task;

import duke.DukeException.DukeException;

/**
 * Represents task belong to deadline.
 */
public class Periods extends Task {
    /**
     * Deadline of the task.
     */
    private String period;

    /**
     * Creates the deadline task.
     * @param name Description of the task.
     * @param period Period of the task need to be done.
     */
    public Periods (String name, String period) throws DukeException {
        super(name);
        this.period = period;
    }

    /**
     * Creates the string to be saved in storage.
     * @return String that will be saved in storage.
     */
    @Override
    public String writeString() {
        if (this.getMarkStatus()) {
            return "P,0" + this.getName() + "," + this.period + "\n";
        } else {
            return "P,1," + this.getName() + "," + this.period + "\n";
        }
    }

    /**
     * Converts the task to a string.
     * @return String that represent the deadline.
     */
    @Override
    public String toString() {
        return "[P]" + super.toString() + "(between: "+ this.period +")";
    }

    /**
     * Checks whether the input is a valid deadline.
     * @param input Task that will be checked.
     * @return Boolean that represent whether the input is a deadline.
     * @throws DukeException Exception where the deadline is not valid.
     */
    public static boolean isPeriod(String input) throws DukeException {
        if (input.split(" ")[0].equals("period")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS! The description of period cannot be empty.");
            } else if (!input.contains("/between ")) {
                throw new DukeException("OOPS! The description of period does not contain /between.");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}