public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String formatToSave() {
        return "T" + super.formatToSave();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
