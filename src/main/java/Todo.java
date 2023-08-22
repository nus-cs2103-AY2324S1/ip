public class Todo extends Task{
    public Todo(String description) {
        //no extra information for todolist
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + super.description;
    }
}
