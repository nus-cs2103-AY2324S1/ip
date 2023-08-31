import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = DateHelper.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_DATETIME_FORMAT);
        }
    }

    @Override
    public void writeToFile(String path) {
        try {
            FileWriter file = new FileWriter(path, true);
            int completed = this.isDone ? 1 : 0;
            file.write("D " + "| " + completed + " | " + this.description + " | "
                    + DateHelper.saveFormat(this.by) + "\r\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateHelper.format(by) + ")";
    }
}
