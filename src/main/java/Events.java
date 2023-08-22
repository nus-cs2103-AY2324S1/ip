/**
 * Encapsulates the Events Task.
 * @author Donovan Chan Jia Jun
 */
public class Events extends Task {
    private String from;
    private String to;
    public Events(String name, String from, String to) {
        super(name);
        this.to = to;
        this.from = from;
    }

    /**
     * Retrieves the String representation of the Events object.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)", super.getMarking(), super.name, this.from, this.to);
    }
}
