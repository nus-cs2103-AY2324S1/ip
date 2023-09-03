package duke.task;

import duke.Ui;
import duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static Deadline initializeFromInput(String input) throws Exception {
        try {
            String[] processed = input.split("deadline")[1].split("/by");
            String taskName = processed[0];
            LocalDate doBy = LocalDate.parse(processed[1].strip());
            return new Deadline(taskName, doBy);
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date time format:" + e.getMessage());
        } catch (Exception e) {
            throw new EmptyDescriptionException("deadline", "deadline return book /by 2019-10-15");
        }
    }

    public static Deadline initializeFromStorage(String input) {
        String[] processed = input.split("\\(");
        String taskName = processed[0].trim();
        String dateString = processed[1].split("by:")[1].split("\\)")[0].trim();
        LocalDate doBy = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Deadline(taskName, doBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Ui.outputDate(by) + ")";
    }
}
