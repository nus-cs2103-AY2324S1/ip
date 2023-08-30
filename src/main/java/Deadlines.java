import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private String datetime;
    private LocalDateTime parsedDatetime;

    public Deadlines(String name, String datetime) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedDatetime = LocalDateTime.parse(datetime, formatter);
        this.datetime = datetime;
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDatetime = this.parsedDatetime.format(formatter);
        String description = String.format("%s (by: %s)", super.getName(), formattedDatetime);
        return description;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + datetime;
    }
}
