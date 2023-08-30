package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Event extends Task{
    LocalDateTime from;
    LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(dateTimeOutputFormatter)
                + " to: " + this.to.format(dateTimeOutputFormatter) + ")";
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.EVENT.getCommand(), this.getDone() ? "1" : "0", this.getTaskName(),
                this.from.format(Duke.dateTimeInputFormatter), this.to.format(Duke.dateTimeInputFormatter) };
        return String.join(" / ", state);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.from.toLocalDate()) || date.isEqual(this.to.toLocalDate()) ||
                (date.isAfter(this.from.toLocalDate()) && date.isBefore(this.to.toLocalDate()));
    }

    public static String[] processInput(String[] splitInput) throws InvalidTaskException {
        splitInput = Task.processInput(splitInput);
        if (splitInput.length < 3) {
            throw new InvalidTaskException("☹ OOPS!!! The description, start and end of a event cannot be empty.");
        }
        String[] startArray = splitInput[1].split(" ");
        splitInput[1] = String.join(" ", Arrays.copyOfRange(startArray, 1, startArray.length));
        String[] endArray = splitInput[2].split(" ");
        splitInput[2] = String.join(" ", Arrays.copyOfRange(endArray, 1, endArray.length));
        return splitInput;
    }
}
