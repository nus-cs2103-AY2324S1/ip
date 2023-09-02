package evo.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import evo.exceptions.InvalidDateAndTimeInputException;

/**
 * Represents a task that need to be done before a specific date/time.
 * This class is a subclass of the Task class and inherits its properties and methods.
 */
public class Deadline extends Task {

    /**
     * The deadline for the task.
     */
    protected String by;

    /**
     * The formatted deadline for the task.
     */
    protected String formatDateAndTime;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Sets the formatted date and time based on the input "by" string for a Deadline task.
     * The method parses the input "by" string and formats it into a user-friendly format.
     * The input "by" string should follow the pattern: "{date}" or "{date} {time}".
     * The date should be in the "yyyy-MM-d" format, and the time should be in "HHmm" format.
     *
     * @throws InvalidDateAndTimeInputException If the input "by" string is not in the expected format or if the
     * date/time values are invalid.
     */
    public void setFormatDateAndTime() {
        String[] dateInput = this.by.split(" ");
        try {
            if (dateInput[0].contains("/")) {
                throw new InvalidDateAndTimeInputException("Please type in a valid date/time input. Eg: deadline return"
                        + " book /by 2019-10-15 1800\n");
            }
            if (dateInput.length == 1) {
                if (dateInput[0].contains("-")) {
                    DateTimeFormatter inputTypes = DateTimeFormatter.ofPattern("yyyy-MM-d");
                    LocalDate date = LocalDate.parse(dateInput[0], inputTypes);
                    formatDateAndTime = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                }
            } else if (dateInput.length == 2) {
                DateTimeFormatter inputTypes = DateTimeFormatter.ofPattern("yyyy-MM-d");
                LocalDate date = LocalDate.parse(dateInput[0], inputTypes);
                String formatDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                LocalTime time = LocalTime.parse(dateInput[1], DateTimeFormatter.ofPattern("HHmm"));
                String formatTime = time.format(DateTimeFormatter.ofPattern("ha"));
                formatDateAndTime = formatDate + " " + formatTime;
            }
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            System.out.println("Please type in a valid date/time input. Eg: deadline return book /by 2019-10-15 1800\n");
        }
    }

    /**
     * Generates a formatted output message representing the Deadline task.
     * The output message includes task details such as task type, completion status, description, and date/time.
     *
     * @return A formatted string representing the Deadline task details.
     */
    @Override
    public String outputMsg() throws InvalidDateAndTimeInputException {
        if (this.by.contains("/")) {
            throw new InvalidDateAndTimeInputException("Please type in a valid date/time input. Eg: deadline return "
                    + "book /by 2019-10-15 1800\n");
        }
        if (this.by.contains("-")) {
            int result = (isDone) ? 1 : 0;
            setFormatDateAndTime();
            return "D | " + result + " | " + description + " | " + formatDateAndTime;
        } else {
            int result = (isDone) ? 1 : 0;
            // Read form the text file while retrieve the data from it
            return "D | " + result + " | " + description + " | " + this.by;
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The string includes task details such as task type, completion status, description, and deadline date/time.
     *
     * @return A string representing the Deadline task details.
     */
    @Override
    public String toString() {
        if (this.by.contains("-")) {
            setFormatDateAndTime();
            return "[D]" + super.toString() + " (by: " + formatDateAndTime + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }
}
