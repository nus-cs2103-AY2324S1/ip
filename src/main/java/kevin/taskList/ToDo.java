package kevin.taskList;

public class ToDo extends Task{
    public ToDo(Boolean isDone, String name) {
        super(isDone, name);
    }

    public String toText() {
        return "Todo - "  + isDone + " - " + name + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
