package fishron;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}