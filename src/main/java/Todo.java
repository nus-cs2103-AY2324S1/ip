public class Todo extends Task {
    /**
     * Constructor for the Todo class
     * 
     * @param task  - the description of the task created
     * @param index - the id of the task created
     */
    public Todo(String task, int index) {
        super(task, index);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
