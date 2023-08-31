package task;

public class TodoTask extends Task{

    /**
     * The constructor for the TodoTask class
     *
     * @param name The name of the task
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * Get the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
