public class ToDo extends Task {
    public ToDo(String task) {
        super(formatToDoString(task));
    }

    /*
     * Formats the task string by slicing it to remove the "todo " part.
     * 
     * @param taskString the string that contains the task
     * @return           the clean version of the task string
     */
    public static String formatToDoString(String taskString) {
        return taskString.substring(5);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}