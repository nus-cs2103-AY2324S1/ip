import java.io.Serializable;
public class Task implements Serializable {
    protected boolean marked;
    protected String description;
    public Task(String description) {
        this.description = description;
        this.marked = false;
    }
    public String getStatusIconWithBracket() {
        return marked ? "[X]" : "[ ]";
    }
    public String getStatusIcon() {
        return marked ? "X" : "O";
    }
    public void markAsDone() {
        this.marked = true;
    }
    public String getDescription() {
        return this.description;
    }

    public void markAsUnDone() {
        this.marked = false;
    }
    @Override
    public String toString() {
        return this.getStatusIconWithBracket() + " " + description;
    }

    public String toFileString() {
        return " | " + this.getStatusIcon() + " | " + this.getDescription();
    }

}
