public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]" : "[ ]";
        return "[T]" + done + " " + this.name;
    }
}
