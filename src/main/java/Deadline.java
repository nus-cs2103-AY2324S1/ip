import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String due;

    public Deadline(String task, String due) throws DateTimeException  {
        super(task);
        this.due = LocalDate.parse(due).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String task, boolean isDone, String due) throws DateTimeException {
        super(task, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due + ")";
    }

    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "D|" + (this.isDone ? "1|" : "0|") + this.getTask() + "|" + this.due;
        fw.write(storedRow);
    }
}
