package duke.task;
public class Todo extends Task{

    /**
     * Constructor
     *
     * @param des Description of the task.
     */
    public Todo(String des) {
        super(des);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
