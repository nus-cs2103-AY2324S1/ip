import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends UserInput {
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
