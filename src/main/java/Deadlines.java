/**
 * Encapsulates the Deadline Task.
 * @author Donovan Chan Jia Jun
 */
public class Deadlines extends Task{
    private String deadline;
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Retrieves the String representation of the Deadline object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getMarking(), super.name, this.deadline);
    }
}
