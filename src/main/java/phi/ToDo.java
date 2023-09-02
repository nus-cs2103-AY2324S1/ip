package phi;

/**
 * Represents the ToDo task type
 */
public class ToDo extends Task {

    /**
     * Constructor for a new ToDo instance
     * @param msg       Task message to be displayed
     * @param isDone    Boolean determining if task is completed
     */
    public ToDo(String msg, boolean isDone) {
        super(Type.T,isDone, msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s", taskType.toString(), done, taskName);
    }

}



