import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    private String type;
    private Line line = new Line();
    private String description;
    private boolean isDone = false;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
    }

    abstract String getOriginalMessage();

    public void mark(boolean val, boolean isRestoring) {
        this.isDone = val;
        if (!isRestoring) {
            System.out.println(line);
            if (val) {
                System.out.println("    Nice! I've marked this task as done:");
            } else {
                System.out.println("    OK, I've marked this task as not done yet:");
            }
            System.out.println("      " + this);
            System.out.println(line);
        }
    }

    public void save(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(String.format("%s%s\n", this.getOriginalMessage(), this.isDone ? "1" : "0"));
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("    Error saving to file");
            System.exit(1);
        }
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    @Override

    public String toString() {
        String s = String.format("[%s][%s] %s", this.type.substring(0,1).toUpperCase(), this.getStatusIcon(), this.description);
        return s;
    }
}
