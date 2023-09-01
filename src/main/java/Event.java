import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String end;
    protected String start;

    protected LocalDateTime formattedStart;
    protected LocalDateTime formattedEnd;

    public Event(String description, String start, String end) {
        super(description.trim());
        this.start = start;
        this.end = end;

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss");
            formattedStart = LocalDateTime.parse(this.start, formatter);
            formattedEnd = LocalDateTime.parse(this.end, formatter);
        } catch (DateTimeException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String store(){
        return String.format("E | %s | %s | %s - %s", this.isDone, this.description,
                formattedStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                formattedEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                formattedStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                formattedEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}

