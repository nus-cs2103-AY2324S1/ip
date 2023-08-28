public class Todo extends Task {
    public Todo(String task) throws DukeException{
        super(task);
    }

    public Todo(String task, boolean finish) throws DukeException{
        super(task, finish);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
