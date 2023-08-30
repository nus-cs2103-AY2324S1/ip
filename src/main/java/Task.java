import java.io.FileWriter;
import java.io.IOException;

public class Task {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void toggleDone() {
        this.done = !this.done;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    public String getTaskDesc() {
        return "";
    };

    public void save(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(this.getTaskDesc() + "\n");
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("    Error saving to file");
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        if (!this.done) {
            return "[ ] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}