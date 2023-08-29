package tasks;

/**
 * A todo task.
 */
public class TodoTask extends Task {

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
        String output = String.format("[T]%s", super.toString());
        return output;
    }

}
