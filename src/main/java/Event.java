import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


class Event extends Task {
    final LocalDate from;
    final LocalDate to;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    public Event(boolean done, String desc) throws DukeException {
        super(done, desc.substring(6, desc.indexOf("/from")));
        int fromIndex = desc.indexOf("/from");
        int toIndex = desc.indexOf("/to");

        try {
            String fromString = desc.substring(fromIndex + 6, toIndex).replace(" ", "");
            String toString = desc.substring(toIndex + 4).replace(" ", "");

            this.from = LocalDate.parse(fromString);
            this.to = LocalDate.parse(toString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter the event in the correct format!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format! Use eg.2019-12-02");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }
}
