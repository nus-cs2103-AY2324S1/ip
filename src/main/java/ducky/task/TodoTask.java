package ducky.task;

public class TodoTask extends Task {

    public TodoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getSaveFormat() {
        return String.format("T | %s", super.getSaveFormat());
    }
}
