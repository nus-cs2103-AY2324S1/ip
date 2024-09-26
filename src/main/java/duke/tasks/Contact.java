package duke.tasks;

import duke.exceptions.DukeException;

public class Contact extends Task {

    private final String[] descriptionArray;
    public Contact(String[] descriptionArray) {
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
            throw new DukeException("You are missing some contact details");
        }
    }

    /**
     * Reformats events to be ready to be written into tasks.txt.
     * Should print in this format:
     * C | 1 | contact <name> | <number> | <reason>
     *
     * @return the reformatted event.
     */
    public String writtenFormat() {
        String[] task = descriptionArray[0].split("contact ");
        String contactName = task[1];
        String contactNumber = descriptionArray[1];
        String contactReason = descriptionArray[2];
        return "C | " + super.status() + " | " + contactName + " | " + contactNumber + " | " + contactReason;
    }
    @Override
    public String toString() {
        String contactNumber = "@ " + descriptionArray[1];
        String contactReason = "for " + descriptionArray[2];
        return "[C]" + super.toString() + " " + contactNumber + " " + contactReason;
    }
}