package emiya.task;

public class ToDo extends Task {
    public ToDo(boolean isCompleted, String nameOfTask) {
        super(isCompleted, nameOfTask);
    }

    @Override
    public String toString() {
        if (isCompleted) {
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
