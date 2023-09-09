package chatterbot.data;

/**
 * Represents a task that just has a description.
 */
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Invalid input! No task description.");
        }
    }

    /**
     * Returns the description of the todo task.
     * @return String This is the todo description in the format it will be displayed in, in the list.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the input for the Chatterbot.txt file in the same format as was entered by the user.
     * @return String This is the formatted line to add to the ChatterBot.txt file.
     */
    @Override
    public String formatForFile() {
        return "todo " + this.description;
    }
}