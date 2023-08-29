package seedu.duke.tasks;

/**
 * Todo class
 */
public class Todo extends Task {

    /**
     * Todo constructor
     *
     * @param description user input
     * @param isMarked is task marked
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked); // initializes its task
    }

    /**
     * formats task for storage in duke.txt
     *
     * @return proper string format
     */
    public String writeFormat() {
        int isDone = 0;
        if (super.isMarked()) {
            isDone = 1;
        }
        return "T" + " | " + isDone + " | " + super.getDescription();
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
