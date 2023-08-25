public class ToDo extends Task {
    private static String noDescErrorMsg = "\u2639 OOPS!!! The description of a todo cannot be empty.";

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
        if (checkTaskNoDescription(taskString, "todo")) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }

        String output = taskString.substring(5);
        if (checkAllWhiteSpace(output)) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }
        
        return output.trim();
    }

    @Override
    public String stringToSave() {
        return "T" + super.stringToSave(); 
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}