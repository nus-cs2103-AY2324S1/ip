import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadline;
    private String symbol = "[D]";
    public Deadline(String name, String deadline) {
        super(name);
        verifyDeadline(deadline);
    }
    public void verifyDeadline(String date) {
        //pretty hard coded here, i think can be improved later eg if 1 d provided
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
            String formattedDate = dateTime.format(outputFormatter);
            this.deadline = formattedDate;
        } catch (DateTimeParseException e) {
            //not in expected format
            this.deadline = date;
        }
    }

    @Override
    public String newFormat() {
        return this.symbol + " | " + this.getInt() + " | " + this.getName() + " | " + this.deadline;
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName() + " (by: " + deadline + ")";
    }
}