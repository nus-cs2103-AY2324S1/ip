public class Todo extends Task {
    /**
     * Constructor for the Todo class
     * 
     * @param task - the description of the task created
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
