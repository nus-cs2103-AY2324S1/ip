import java.io.Serializable;

public class Task implements Serializable {
    private final String name;
    private boolean marked = false;

    public Task(String name) {
        this.name = name;
    }

    public void markTask() {
        this.marked = true;
    }

    public void unMarkTask() {
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String getName() {
        return this.name;
    }
}