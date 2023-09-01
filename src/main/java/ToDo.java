/**
 * ToDo tasks have no deadline.
 */
public class ToDo extends Task{

    /**
     * Instantiates an instance of a ToDo task class.
     * @param taskname the name of the task.
     */
    public ToDo(String taskname) {
        super(taskname);
    }


    /**
     * Shows the name of the ToDo task and its status.
     *
     * @return Name of the ToDo task and its current status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
