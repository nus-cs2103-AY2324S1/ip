import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Deadline extends Task{
    LocalDateTime by;
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(dateTimeOutputFormatter) + ")";
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.DEADLINE.getCommand(), this.getDone() ? "1" : "0", this.getTaskName(),
                this.by.format(Duke.dateTimeInputFormatter) };
        return String.join(" / ", state);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.by.toLocalDate());
    }

    public static String[] processInput(String[] splitInput) throws InvalidTaskException {
        splitInput = Task.processInput(splitInput);
        if (splitInput.length < 2) {
            throw new InvalidTaskException("☹ OOPS!!! The description and date of a deadline cannot be empty.");
        }
        String[] deadlineArray = splitInput[1].split(" ");
        splitInput[1] = String.join(" ", Arrays.copyOfRange(deadlineArray, 1, deadlineArray.length));
        return splitInput;
    }
}
