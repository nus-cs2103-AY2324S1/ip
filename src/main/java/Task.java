import java.time.LocalDate;

public class Task {
    protected final String description;
    protected final Boolean marked;

    Task(String description, Boolean marked) {
        this.description = description;
        this.marked = marked;
    }

    Task(String description) {
        this.description = description;
        this.marked = false;
    }

    String getType() {
        return "None";
    }

    String getDescription() {
        return this.description;
    }

    Boolean isMarked() {
        return this.marked;
    }

    Boolean happenOnThatDate(LocalDate date) {
        return false;
    }

    Task mark() {
        return new Task(this.description, true);
    }

    Task unmark() {
        return new Task(this.description, false);
    }
    @Override
    public String toString() {
        return "[" +  (this.marked ? "X" : " ") + "] " + this.description;
    }
}
