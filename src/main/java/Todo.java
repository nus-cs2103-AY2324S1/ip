public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeFile() {
        return "T | " + super.writeFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
