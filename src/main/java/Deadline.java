import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    final LocalDate by;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    public Deadline(boolean done, String desc) throws DukeException {
        super(done, desc.substring(9, desc.indexOf("/by")));
        String byString = desc.substring(desc.indexOf("/by") + 4).replace(" ", "");
        try {
            this.by = LocalDate.parse(byString);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format! Use eg.2019-12-02");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(formatter) + ")";
    }
}
