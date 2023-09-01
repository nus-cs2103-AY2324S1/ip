package duke.task;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class, which is a type of class.
 */
public class Deadline extends Task {

    protected String by;

    protected LocalDate date;

    /**
     * A constructor of the Deadline class, which can parse
     * a deadline with input of description and by.
     * @param description The description of the deadline.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = null;
    }

    /**
     * A constructor of the Deadline class, which can parse
     * a deadline with input of description, by and a date.
     * @param description The description of the deadline.
     * @param by The deadline.
     * @param date A date in LocalDate format.
     */
    public Deadline(String description, String by, LocalDate date) {
        super(description);
        this.by = by;
        this.date = date;
    }

    /**
     * Read the Task data and add it into ArrayList.
     * @param list An ArrayList of Tasks.
     * @param data Data to be read and stored in list.
     */
    public static void readData(ArrayList<Task> list, String data) {
        String splitDeadline[] = data.split(" \\| ");

        Task deadlineTask;
        try {
            LocalDate d1 = LocalDate.parse(splitDeadline[3]);
            deadlineTask = new Deadline(splitDeadline[2] + " ", splitDeadline[3], d1);
        } catch (DateTimeParseException e) {
            deadlineTask = new Deadline(splitDeadline[2], splitDeadline[3]);
        }

        list.add(deadlineTask);
        if (splitDeadline[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }


    /**
     * Default System.out when this function is called.
     * @return string information in the Deadline instance.
     */
    @Override
    public String toString() {
        if (this.date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + getDate();
        }
    }

    /**
     * Get a string representation of date in the MMM d yyyy format.
     * @return A string representation of date in MMM d yyyy format.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}