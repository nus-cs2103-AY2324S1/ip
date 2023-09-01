package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;

public class Event extends Task {

    private String from;
    private String to;


    public Event(String task, boolean isDone, String from, String to) throws DateTimeException {
        super(task, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "E|" + (this.isDone ? "1|" : "0|") + this.getTask() + "|" + this.from + "|" + this.to;
        fw.write(storedRow);
    }

}
