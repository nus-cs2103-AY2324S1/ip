import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            System.out.println(from);
            this.from = DateHelper.parse(from);
            this.to = DateHelper.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_DATETIME_FORMAT);
        }
    }

    @Override
    public void writeToFile(String path) {
        try {
            FileWriter file = new FileWriter(path, true);
            int completed = this.isDone ? 1 : 0;
            file.write("E " + "| " + completed + " | " + this.description + " | " + DateHelper.saveFormat(this.from)
                    + " | " + DateHelper.saveFormat(this.to) + "\r\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateHelper.format(from) + " to: " + DateHelper.format(to) + ")";
    }
}
