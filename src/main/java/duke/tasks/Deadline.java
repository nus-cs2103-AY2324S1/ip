package duke.tasks;

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
     * Method that checks that 1. task contains deadline, 2. deadline in specified format.
     *
     * @return reformatted deadline
     * @throws DukeException when input is invalid.
     */
    public String checkValidity() throws DukeException {
        String[] descrArr = descr.split("/by "); //you get 0: taskName, 1: deadline
        String date = descrArr[1];
        String res;
        if (descrArr.length < 2) {
            throw new DukeException("You are missing the deadline");
        }

        boolean isDay = false;
        try {
            DayOfWeek.valueOf(date.toUpperCase());
            isDay = true;
        } catch (IllegalArgumentException e) {
        }

        if (isDay) {
            res = date;
        } else {
            try {
                LocalDate deadline = LocalDate.parse(date);
                res = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                throw new DukeException(e.getMessage() + "\n"
                        + "Make sure you've either inputted a valid day or in yyyy-mm-dd format (e.g. 2019-10-15)");
            }
        }
        return res;
    }

    /**
     * Method that reformats deadlines to be ready to be written into tasks.txt.
     *
     * @return the reformatted deadline.
     */
    public String writtenFormat() {
        String res = "Invalid Deadline";
        try {
            res = "Invalid Deadline";
            String[] parts = this.descr.split("/by");
            String eventType = "deadline";
            String eventDescription = parts[0].substring(eventType.length()).trim();
            res = "D | " + super.status() + " | " + eventDescription + " | " + checkValidity();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    /**
     * Method that converts task to string.
     *
     * @return formatted String version of the task
     */
    @Override
    public String toString() {
        String res = "Invalid Deadline";
        try {
            //String deadline = this.descr.split("/by")[1].trim();
            res = "[D]" + super.toString() + " (by: " + checkValidity() + ")";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
