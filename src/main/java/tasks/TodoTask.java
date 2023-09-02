package tasks;

/**
 * A todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructor, initializes task description and isMarked.
     *
     * @param desc Task description.
     * @param isMarked 1 if is marked, 0 otherwise.
     */
    public TodoTask(String desc, int isMarked) {
        super(desc);
        if (isMarked == 1) {
            this.isDone = true;
        }
    }

    @Override
    protected String getTextFormattedString() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.desc);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
