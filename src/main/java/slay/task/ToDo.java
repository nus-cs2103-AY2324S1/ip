package slay.task;

/**
 * Represents ToDo Task.
 */
public class ToDo extends Task {
    public static final String TYPE = "todo";

    public ToDo(String description) {
        super(description);
    }

    public ToDo(Boolean isDone, String description) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns encoded ToDo Task.
     *
     * @return String representation of the ToDo Task to be saved into the data file.
     */
    @Override
    public String getEncodedString() {
        String encoded = "T | ";

        if (super.isDone) {
            encoded += "1";
        } else {
            encoded += "0";
        }

        encoded += " | " + super.getTag() + " | " + super.getDescription();

        return encoded;
    }
}
