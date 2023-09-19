package cringebot.tasks;

/**
 * Class to create an event.
 */
public class Event extends Task {

    /**
     * Constructor for Event.
     *
     * @param name text inputted by user for the Event.
     */
    public Event(String name) {
        super(name);
    }

    /**
     * returns string representation, with task type, marked, content and period.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String checkBox;
        char taskType1 = 'E';
        String taskType = String.format("[%c]", taskType1);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        System.out.printf("%s%s %s%n", taskType, checkBox, super.getName());
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}
