package task;

/**
 * todo class
 */
public class ToDo extends Task {

    /**
     * Constructor for todo task
     *
     * @param description Task description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[T]");
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        return sb.toString();
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (!(task instanceof ToDo)) {
            return false;
        }
        ToDo toDo = (ToDo) task;
        if (toDo.description.equals(this.description)) {
            return true;
        } else {
            return false;
        }
    }
}
