public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    public String identifier() {
        return "T";
    }
}
