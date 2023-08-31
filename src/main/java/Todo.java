public class Todo extends Task {
    public Todo(String item) {
        super(item);
    }
    public Todo(String item, boolean done) {
        super(item, done);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String saveItem() {
        return "T | " + super.saveItem() + "\n";
    }
}