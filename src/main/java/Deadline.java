import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String input;
    private String time;

    public Deadline(String description, String input) {
        super(description);
        this.input = input;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getExtras() {
        String[] dateDetails = input.split(" ", 2);

        LocalDate localDate = LocalDate.parse(dateDetails[0]);
        time = " " + dateDetails[1];

        return "(by: " + 
                    localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                        + time + ")";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() +
                    super.description + getExtras();
    }
}