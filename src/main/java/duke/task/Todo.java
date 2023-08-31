package duke.task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public static Todo readFromFile(String[] components) {
        boolean isDone = components[1].equals("1");
        Todo todo = new Todo(components[2]);
        if(isDone) {
            todo.markDone();
        }
        return todo;
    }
    @Override
    public String writeFileFormat() {
        return "T|" + super.writeFileFormat();
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
