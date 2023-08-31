import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public void writeToFile(String path) {
        try {
            FileWriter file = new FileWriter(path, true);
            int completed = this.isDone ? 1 : 0;
            file.write("T " + "| " + completed + " | " + this.description + "\r\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

