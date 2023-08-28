package task;

public class ToDo extends Task {
    public ToDo(String nameOfTask) {
        super(nameOfTask);
    }

    @Override
    public String toString() {
        if (completed) {
            return "[T][X] " + nameOfTask;
        } else {
            return "[T][ ] " + nameOfTask;
        }
    }

    @Override
    public String typeOfString() {
        return "T ";
    }

    @Override
    public String taskDetailsString() {
        return super.nameOfTask;
    }

}
