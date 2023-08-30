import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String startDateTime;
    private String endDateTime;

    private LocalDateTime startInDateTime;
    private LocalDateTime endInDateTime;


    public Event (String description) {
        super(description.split("/from")[0]);

        String[] parts = description.split("\\s*/from\\s*|\\s*/to\\s*");
        this.startDateTime = parts[1];
        this.endDateTime = parts[2];


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "[yyyy-MM-dd HH:mm:ss][dd-MM-yyyy][MM/dd/yyyy HH:mm:ss][dd/MM/yyyy]"
        );

        try {
            startInDateTime = LocalDateTime.parse(this.startDateTime, formatter);
            endInDateTime = LocalDateTime.parse(this.endDateTime, formatter);
        } catch (DateTimeParseException e) {
            // If datetime parsing fails, try parsing as date-only
            try {
                LocalDate startDate = LocalDate.parse(this.startDateTime, formatter);
                LocalDate endDate = LocalDate.parse(this.endDateTime, formatter);
                startInDateTime = startDate.atStartOfDay();
                endInDateTime = endDate.atStartOfDay();
            } catch (DateTimeParseException ex) {
                System.out.println("Error parsing datetime: " + ex.getMessage());
            }
        }

    }


    public String type() {
        return "E";
    }

    public String getStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = startInDateTime.format(formatter);
        return text;
    }

    public String getEndDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = endInDateTime.format(formatter);
        return text;
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s/from%s/to%s", this.type(), this.isDone, this.description, getStartTime(), getEndDateTime());
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(from:%s to:%s)", type, super.toString(), getStartTime(), getEndDateTime() );
    }
}
