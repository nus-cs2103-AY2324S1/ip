package duke.task;

public class ToDo extends Task{

    /**
     * Creates a new 'ToDo' object with text and set type "T" with super constructor from Task
     *
     *
     * @param text The text description of the task.
     */
    public ToDo (String text) {
        super(text);
        super.setType("T");
    }

    /**
     * Creates a new todo object with the given text and checked status also set Type as "T"
     * Mainly used for registering input from local file
     *
     * @param text The text description of the task.
     * @param checked The status of whether the task is checked (completed) or not.
     */
    public ToDo (String text,boolean checked) {
        super(text,checked);
        super.setType("T");
    }

}
