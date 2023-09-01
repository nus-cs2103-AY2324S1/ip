package task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    public ToDo(String name, boolean completeStatus) {
        super(name, completeStatus);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String fileFormat() {
        return "TD" + DIVIDER + super.fileFormat();
    }

}
