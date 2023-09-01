package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String s, String by) throws DateTimeParseException {
        super(s);
        try {
            this.by = DPUtils.dPTryParseDateTime(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'by'. Please provide a valid date format.", e);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DPUtils.dPFormatDateTime(this.by));
    }
}
