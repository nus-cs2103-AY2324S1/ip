public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }
    public String getType() {
        return "todo";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
