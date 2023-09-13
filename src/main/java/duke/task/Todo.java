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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Todo) {
            Todo temp = (Todo) obj;
            if (temp.description.equals(this.description)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
