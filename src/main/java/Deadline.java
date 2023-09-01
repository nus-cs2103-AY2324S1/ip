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

<<<<<<< HEAD
<<<<<<< .merge_file_iBxDM4

=======
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + this.by;
    }
>>>>>>> branch-Level-7
=======
    public String toSaveFormat() {
        return "D" + super.toSaveFormat() + " | " + this.by;
    }
=======

>>>>>>> branch-Level-8
>>>>>>> .merge_file_Olbn4O

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }
}
