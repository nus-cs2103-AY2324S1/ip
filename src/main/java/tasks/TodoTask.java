package tasks;
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask() {
        super("");
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + super.getStatusIcon() + " | " + this.getDescription();
    }

    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.setDescription(fileStringArray[2]);
    }
}
