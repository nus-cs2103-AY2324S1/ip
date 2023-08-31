import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");


    public String getDateTime(String s) {
        LocalDateTime localDateTime = LocalDateTime.parse(s, inputFormat);
        String dateTime = localDateTime.format(outputFormat);
        return dateTime;
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String displayTask() {
        return (".[D]" + super.getStatusIcon() + description + "(by: " + getDateTime(by) + ")");
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D]" + super.getStatusIcon() + description + "(by: " + getDateTime(by) + ")");
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[D]" + super.getStatusIcon() + description + " (by: " + getDateTime(by) + ")");
    }


    public static Deadline readFromFile(String[] segments) {
        String symbol = segments[1];
        String description = segments[2];
        String by = segments[3];
        Deadline toReturn =  new Deadline(description, by);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
    }

    @Override
    public String toWriteString() {
        String toWrite = "D" + " | " + super.toWriteString() + " | " + by;
        return toWrite;
    }


}
