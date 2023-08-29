import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void writeToFile(FileWriter fw) throws IOException {
        String storedRow = "T|" + (this.isDone ? "1|" : "0|") + this.getTask();
        fw.write(storedRow);
    }
}
