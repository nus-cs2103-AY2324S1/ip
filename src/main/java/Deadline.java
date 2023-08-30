import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String time;
    private LocalDateTime timeInDateTime;

    public Deadline(String description) {

        super(description.split("/by")[0].trim());
        this.time = description.split("/by")[1].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][yyyy-MM-dd][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy]"
        );

        try {
            timeInDateTime = LocalDateTime.parse(this.time, formatter);
        } catch (DateTimeParseException e) {
            // If datetime parsing fails, try parsing as date-only
            try {
                LocalDate date = LocalDate.parse(this.time, formatter);
                timeInDateTime = date.atStartOfDay();
            } catch (DateTimeParseException ex) {
                System.out.println("Error parsing datetime: " + ex.getMessage());
            }
        }
    }

    public String type() {
        return "D";
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = timeInDateTime.format(formatter);
        return text;
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s/by%s", this.type(), this.isDone, this.description, getTime());
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(by:%s)", type, super.toString(), getTime() );
    }

}
