package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }


    public String exportData() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
