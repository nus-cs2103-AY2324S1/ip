package taskmaster.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    /**
     * Date that the deadline must be done by
     */
    LocalDate date;
    /**
     * String representation that the deadline must be done by
     */
    String deadline;
     /**
      * Constructor for the Deadline class.
      *
      * @param description Description of the Deadline.
      * @param deadline Time that the deadline must be completed by.
      * @param marked Boolean whether the deadline is marked.
      */
     public Deadline(String description, String deadline, String marked) {
         super(description, marked);
         try {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate parsedDate = LocalDate.parse(deadline, formatter);
             this.date = parsedDate;
         } catch (java.time.format.DateTimeParseException e) {
             this.deadline = deadline;
         }
     }
     /**
      * Returns a string representation of the start time.
      *
      * @return A string representing the time of the deadline.
      */
     public String getStringDate() {
         return this.deadline;
     }
     /**
      * Returns a LocalDate representation of the start date.
      *
      * @return A LocalDate representing the starting date of the deadline.
      */
     public LocalDate getLocalDate() {
         return this.date;
     }
     /**
      * Returns a string representation of the deadline.
      *
      * @return A string representing the deadline.
      */
     @Override
     public String toString() {
         return "[D]" + super.toString() + " (by: " + (deadline == null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline) + ")";
     }
}
