import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String task, String[] period) {
        super(task);
        this.from = period[0];
        this.to = period[1];
    }

    public Event(String task, boolean isDone, String from, String to) {
        super(task, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "E|" + (this.isDone ? "1|" : "0|") + this.getTask() + "|" + this.from + "|" + this.to;
        fw.write(storedRow);
    }

}
