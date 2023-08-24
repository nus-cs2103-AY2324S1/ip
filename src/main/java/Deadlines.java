/**
 * class for deadline
 */
public class Deadlines extends Task{
    /**
     * The deadline of the task
     */
    private String ddl;

    /**
     * The constructor
     * @param name the name of the deadline task
     * @param ddl the deadline
     */
    public Deadlines (String name, String ddl) {
        super(name);
        this.ddl = ddl;
    }

    /**
     * Convert to string
     * @return a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+ ddl +")";
    }
}