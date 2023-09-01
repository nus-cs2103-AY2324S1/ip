public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public String getDateTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
