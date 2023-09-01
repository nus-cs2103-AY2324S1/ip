package aichan.task;

import aichan.AiChanException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that user need to do with its deadline.
 * Inherits from Task.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs the Deadline object.
     * Initializes description and due date, set it as have not done.
     *
     * @param strs Array of string which first element is description, second element is due date.
     * @throws AiChanException If the format of due date is incorrect.
     */
    public Deadline(String[] strs) throws AiChanException {
        // first is taskName, second element is dueDate
        super(strs[0]);
        // assume that strs[1] follow the format 25/12/2019 1800
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.dueDate = LocalDateTime.parse(strs[1], formatter); // 2019-12-25T18:00
        } catch (DateTimeParseException e) {
            throw new AiChanException("Please enter the date and time with this format: 25/12/2019 1800");
        }
    }

    /**
     * Returns the string representation of the deadline object.
     *
     * @return A string indicates whether the deadline is done followed by its description and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy HHmm"); // Dec 25 2019 1800
        // System.out.println(this.dueDate.format(formatter2));
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(formatter2));
    }

    /**
     * Returns the line of the deadline object to be saved in the file.
     *
     * @return A string indicates whether the deadline is done followed by its description and due date.
     */
    @Override
    public String toFileLine() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format("D | %s | %s", super.toFileLine(), this.dueDate.format(formatter));
    }
}
