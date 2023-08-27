import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate byDate;


    public DeadlineTask(String description, String byDate, boolean isDone) {
        super(description, isDone);
        try {
            setDeadlineByDate(byDate);
        } catch (DateTimeException e) {
            throw new DateTimeException("Please input your date in the YYYY-MM-DD format!");
        }
    }


    public static DeadlineTask parseDeadline(String taskDetails, boolean isDone) {
        if (!taskDetails.contains("by:")) {
            throw new RuntimeException("Error! Remember to include 'by:' after the deadline command!");
        }
        String[] details = taskDetails.split("by:", 2);
        String description = details[0];
        description = description.replace(" ", "");
        String by = details[1];
        by = by.replace(" ", "");

        return new DeadlineTask(description, by, isDone);
    }

    public void setDeadlineByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.byDate = LocalDate.parse(date, formatter);
    }

    public String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return byDate.format(formatter);
    }

    public String toSave() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
