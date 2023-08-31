package duke;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "T " + isCompleted + " " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
