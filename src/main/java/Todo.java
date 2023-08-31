/**
 * Todo is the main class for Todo task used by the Sidtacphi bot.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     * 
     * @param name
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Shows whether the task is completed and the name of the task.
     * 
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();     
    }
}
