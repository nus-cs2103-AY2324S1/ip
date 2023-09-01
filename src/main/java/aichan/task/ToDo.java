package aichan.task;

public class ToDo extends Task{
    public ToDo(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileLine() {
        return String.format("T | %s", super.toFileLine());
    }
}
