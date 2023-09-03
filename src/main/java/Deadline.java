import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    LocalDate deadline;

    Deadline(boolean done, String name) {
        super(done, name);
        String[] dateParse = name.split("by: ");
        String dateParse2 = dateParse[1].split("\\)")[0];
        LocalDate temp = LocalDate.parse(dateParse2);
        this.deadline = temp;
    }

    Deadline(String name) {
        this(false, name);
    }

    @Override
    public String taskType() {
        return "D";
    }

    @Override
    public String toString() {
        String parsedName = this.name.split(" \\(")[0];
        String stringDate = this.deadline
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        String doneString = this.done ? "[X] " : "[ ] ";

        String finalOutput = "[D] " + doneString
            + parsedName + String.format(" (by: %s)", stringDate);

        return finalOutput;
    }
}
