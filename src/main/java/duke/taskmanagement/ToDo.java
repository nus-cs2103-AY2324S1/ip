package duke.taskmanagement;
public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }

    public String saveToFileString() {
        return "T | " +  convertIsDone() + " | " + description + "\n";
    }
}
