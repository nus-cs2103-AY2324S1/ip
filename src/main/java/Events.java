import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private String from;
    private String to;
    private LocalDateTime parsedFrom;
    private LocalDateTime parsedTo;
    public Events(String name, String from, String to) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.parsedFrom = LocalDateTime.parse(from, formatter);
        this.parsedTo = LocalDateTime.parse(to, formatter);
        this.from = from;
        this.to = to;
    }



    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public String getDescription() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = this.parsedFrom.format(formatter);
        String formattedTo = this.parsedTo.format(formatter);
        String description = String.format("%s (from: %s to: %s)", super.getName(), formattedFrom, formattedTo);
        return description;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }
}
