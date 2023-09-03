package tasks;


import java.time.LocalDateTime;

public class Deadline extends Task {
        protected LocalDateTime by;

        public Deadline(String text, LocalDateTime by) {
            super(text);
            this.by = by;
        }

    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }
}
