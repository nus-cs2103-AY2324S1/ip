import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void writeToFile(String path) {
        try {
            FileWriter file = new FileWriter(path, true);
            int completed = this.isDone ? 1 : 0;
            file.write("D " + "| " + completed + " | " + this.description + "| " + this.by + "\r\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
