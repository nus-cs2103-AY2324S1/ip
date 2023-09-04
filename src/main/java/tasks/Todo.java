package tasks;


public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string that represents the Todo
     *
     * @return string with details of the Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that represents the Todo to be stored in txt file
     *
     * @return a formatted string with details of the Todo
     */
    @Override
    public String taskToStringStore(Task task) {
        return "T-" + super.taskToStringStore(task);
    }
}
