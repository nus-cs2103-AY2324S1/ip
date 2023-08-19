public class Deadline extends Task {
    private String deadline;

    public Deadline(String task) {
        super(getTask(task));
        this.deadline = this.getDeadline(task);
    }

    /*
     * Returns an array of 2 elements, the first element is the task, the second is the deadline. 
     * Worth noting that the task and deadline strings here still have whitespaces that need to be
     * trimmed for use.
     * 
     * @param taskString the string that contains the task and deadline
     * @return           an array of 2 strings
     */
    public static String[] splitDeadlineString(String taskString) {
        // removes "deadline "  from the task string
        String removeCmd = taskString.substring(9);
        // we know the array has 2 elements 
        String[] arr = removeCmd.split("/by");
        return arr;
    }

    /**
     * Returns the task from a input string that starts with "deadline".
     * 
     * @param taskString the input string that starts with "deadline"
     * @return           the task
     */
    public static String getTask(String taskString) {
        String[] arr = splitDeadlineString(taskString);
        String task = arr[0];
        // we remove the white space behind the task
        return task.substring(0, task.length() - 1);
    }

    /**
     * Returns the deadline from a input string that starts with "deadline".
     * 
     * @param taskString the input string that starts with "deadline"
     * @return           the deadline
     */
    public String getDeadline(String taskString) {
        String[] arr = splitDeadlineString(taskString);
        String deadline = arr[1];
        // remove the whitespace in front 
        return deadline.substring(1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}