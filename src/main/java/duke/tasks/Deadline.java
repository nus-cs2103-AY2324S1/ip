package duke.tasks;

import duke.Duke;
import duke.exceptions.DukeException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final String descr;

    public Deadline(String descr) {
        super(descr.split("/by")[0]);
        this.descr = descr;
    }

    /**
     * Checks that task contains deadline and that deadline is in specified format.
     * Goes on to reformat date.
     *
     * @return reformatted deadline
     * @throws DukeException if input is invalid.
     */
    public String checkValidity() throws DukeException {
        String[] descrArr = descr.split("/by ");
        String res;

        assert descrArr.length > 2 : "Missing details of deadline";
        if (descrArr.length < 2) {
            throw new DukeException("You are missing details of the the deadline!");
        }

        String date = descrArr[1];
        boolean isDay = false;
        try {
            DayOfWeek.valueOf(date.toUpperCase());
            isDay = true;
        } catch (IllegalArgumentException e) {
            throw new DukeException("This is not a valid day!");
        }

        if (isDay) {
            res = date;
        } else {
            try {
                LocalDate deadline = LocalDate.parse(date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                res = deadline.format(formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Make sure you've either inputted a valid day or in yyyy-mm-dd format (e.g. 2019-10-15)");
            }
        }
        return res;
    }

    /**
     * Reformats deadlines to be ready to be written into tasks.txt.
     *
     * @return the reformatted deadline.
     */
    public String writtenFormat() {
        String res;
        try {
            String[] parts = this.descr.split("/by");
            String eventType = "deadline";
            String eventDescription = parts[0].substring(eventType.length()).trim();
            res = "D | " + super.status() + " | " + eventDescription + " | " + checkValidity();
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }

    /**
     * Converts task to string.
     *
     * @return formatted String version of the task
     */
    @Override
    public String toString() {
        String res;
        try {
            res = "[D]" + super.toString() + " (by: " + checkValidity() + ")";
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }
}
