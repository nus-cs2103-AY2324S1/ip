import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    private String due;

    public Deadline(String task, String due) {
        super(task);
        this.due = due;
    }

    public Deadline(String task, boolean isDone, String due) {
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
