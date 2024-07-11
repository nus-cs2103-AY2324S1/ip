package tasks;

public class TodoTime extends Task {

    private final String duration;

    public TodoTime(String desc, String duration) {
        super(desc);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    /**
     * Returns The string representation of TodoTime.
     * @return String representation of TodoTime.
     */
    @Override
    public String toString() {
        return "[TT]" + super.toString() + " (needs " + duration + ")";
    }
}
