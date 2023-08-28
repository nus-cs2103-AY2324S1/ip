import java.util.ArrayList;
import java.util.List;

abstract public class Task {
    public String description;
    public boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    private String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    abstract public String writeFormat();

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
