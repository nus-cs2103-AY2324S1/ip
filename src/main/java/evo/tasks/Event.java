package evo.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import evo.exceptions.InvalidDateAndTimeInputException;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 * This class is a subclass of the Task class and inherits its properties and methods.
 */
public class Event extends Task {

    /**
     * The duration of the event starts at a specific date/time and ends at a specific date/time.
     */
    protected String duration;

    /**
     * The formatted duration of the event starts at a specific date/time and ends at a specific date/time.
     */
    protected String formatDateAndTime;

    /**
     * Constructs an Event object with the given description and duration.
     *
     * @param description The description of the event.
     * @param duration The duration of the event.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Sets the formatted date and time based on the input duration string.
     * The method parses the input duration and formats it into a user-friendly format.
     * The input duration should follow the pattern: "{date} /from {start_time} /to {end_time}".
     * The date should be in the "yyyy-MM-d" format, and the start and end times should be in "HHmm" format.
     *
     * @throws InvalidDateAndTimeInputException If the input duration is not in the expected format or if the
     * date/time values are invalid.
     */
    public void setFormatDateAndTime() {
        String[] dateInput = this.duration.split(" ");
        try {
            if (dateInput[1].contains("-")) {
                DateTimeFormatter inputTypes = DateTimeFormatter.ofPattern("yyyy-MM-d");
                LocalDate date = LocalDate.parse(dateInput[1], inputTypes);
                String formatDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                LocalTime startTime = LocalTime.parse(dateInput[2], DateTimeFormatter.ofPattern("HHmm"));
                String formatStartTime = startTime.format(DateTimeFormatter.ofPattern("ha"));
                LocalTime endTime = LocalTime.parse(dateInput[4], DateTimeFormatter.ofPattern("HHmm"));
                String formatEndTime = endTime.format(DateTimeFormatter.ofPattern("ha"));

                formatDateAndTime = dateInput[0] + " " + formatDate + " " + formatStartTime + " " + dateInput[3]
                        + " " + formatEndTime;
            } else {
                throw new InvalidDateAndTimeInputException("Please type in a valid date/time input. Eg: event project " +
                        "meeting /from 2019-12-15 1800 /to 2000\n");
            }
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            System.out.println("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000\n");
        }
    }

    /**
     * Generates a formatted output message representing the Event task.
     * The output message includes task details such as task type, completion status, description, and date/time.
     *
     * @return A formatted string representing the Event task details.
     * @throws InvalidDateAndTimeInputException If the input duration is not in the expected format or if the date/time
     * values are invalid.
     */
    @Override
    public String outputMsg() throws InvalidDateAndTimeInputException {
        if (!this.duration.contains("from") || !this.duration.contains("to")) {
            throw new InvalidDateAndTimeInputException("Please type in a valid date/time input. Eg: event project " +
                    "meeting /from 2019-12-15 1800 /to 2000\n");
        }
        if (!this.duration.contains("-")) {
            // read from text file
            return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + this.duration;
        } else {
            setFormatDateAndTime();
            return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + formatDateAndTime;
        }
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes task details such as task type, completion status, description, and  formatted date/time.
     *
     * @return A string representing the Event task details.
     */
    @Override
    public String toString() {
        if (!this.duration.contains("-")) {
            // reads from txt file
            return "[E]" + super.toString() + " (" + this.duration + ")";
        } else {
            setFormatDateAndTime();
            return "[E]" + super.toString() + " (" + formatDateAndTime + ")";
        }
    }
}
