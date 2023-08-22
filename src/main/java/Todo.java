public class Todo extends Task {
    public Todo(String task) throws DukeException{
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
