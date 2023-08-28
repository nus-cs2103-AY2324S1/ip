import java.util.ArrayList;
import java.util.List;

public class Task {
    private String description;
    public boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    private String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
