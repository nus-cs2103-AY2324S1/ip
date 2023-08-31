import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void writeToFile(String path) {
        try {
            FileWriter file = new FileWriter(path, true);
            int completed = this.isDone ? 1 : 0;
            file.write("E " + "| " + completed + " | " + this.description + "| " + this.from
                    + "| " + this.to + "\r\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
