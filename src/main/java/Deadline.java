import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate deadline;
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }


    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "D" + delimiter + status + delimiter + this.description + delimiter + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }

}
