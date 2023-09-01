package duke.task;

public class Todo extends Task {

    /**
     * Constructs a Todo object with the provided description and status.
     *
     * @param description The description of the todo task.
     * @param isDone True if the task is done else false.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Generates the format of the task to be written into the text file.
     *
     * @return The format of the task in the text file.
     */
    @Override
    public String contentLine() {
        return "T" + super.contentLine();
    }

    /**
     * Overrides the 'toString' method of the parent class with to display the task in different format.
     *
     * @return The appearance of the task in the application.
     */
    @Override
    public String toString() {

        String result = "[T]" + super.toString();
        return result;
    }
}
