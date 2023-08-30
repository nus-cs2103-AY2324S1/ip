public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    public String getType() {
        return "Todo";
    }

    public String getDateTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
