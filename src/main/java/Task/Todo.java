package Task;

public class Todo extends Task{
    public Todo(String task) throws DukeException.EmptyTaskDescException {
        super(task);
    }
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSaveFormat(){
        return this.hashCode() + "|todo||" + super.toSaveFormat();
    }
}
