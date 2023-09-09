package miles.task;

/**
 * Represents a todo task that has no dates involved.
 */
public class ToDo extends Task {
    private static String noDescErrorMsg = "OOPS!!! The description of a todo cannot be empty.";

    /**
     * Constructor to create a new todo task.
     * @param task the task
     */
    public ToDo(String task) {
        super(formatToDoString(task));
    }

    /**
     * Formats the task string by slicing it to remove the "todo " part.
     * @param taskString the string that contains the task
     * @return           the clean version of the task string
     */
    public static String formatToDoString(String taskString) {
        if (checkTaskNoDescription(taskString, "todo")) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }

        String output = taskString.substring(5);
        if (checkAllWhiteSpace(output)) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }
        
        return output.trim();
    }

    /**
     * Represents a string to be saved in the text file, specifically for a todo.
     * @return a string that is to be saved in the text file
    */
    @Override
    public String saveStringToFile() {
        return "T" + super.saveStringToFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}