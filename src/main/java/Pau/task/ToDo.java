package Pau.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "T" + delimiter + status + delimiter + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
