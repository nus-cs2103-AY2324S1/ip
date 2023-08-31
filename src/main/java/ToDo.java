/**
 * Todo class that is a task.
 * 
 * 
 * @author Owen Yeo
 */
public class ToDo extends Task {
    
    /**
     * Constructor for a ToDo instance.
     * 
     * @param label descriptor of the tas
     */
    ToDo(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "T " + super.toSaveString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
