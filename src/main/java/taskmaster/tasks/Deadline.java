package taskmaster.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
        LocalDate date;
        String deadline;

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

        public String getStringDate() {
            return this.deadline;
        }

        public LocalDate getLocalDate() {
            return this.date;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + (deadline == null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline) + ")";
        }
}
