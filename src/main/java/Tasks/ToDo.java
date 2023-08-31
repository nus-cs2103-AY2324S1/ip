package Tasks;

public class ToDo extends Task{
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        return "T" + " | " + completionStr + " | " + super.getName();
    }
}
