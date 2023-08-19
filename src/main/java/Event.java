public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String task) {
        super(getTask(task));
        this.startTime = getStartTime(task);
        this.endTime = getEndTime(task);
    }

    /*
     * Returns an array of 3 elements, the first element is the task, the second is the start time,
     * the third is the end time. Worth noting that the task, start time and end time strings here 
     * still have whitespaces that need to be trimmed for use.
     * 
     * @param taskString the string that contains the task, start time and end time
     * @return           an array of 3 strings
     */
    public static String[] splitEventString(String taskString) {
        String removeCmd = taskString.substring(6);
        String[] arr1 = removeCmd.split("/from");
        // we still need to split the 2nd element because it contains both the start and end time
        String secondPart = arr1[1];
        String[] arr2 = secondPart.split("/to");
        String[] output = {arr1[0], arr2[0], arr2[1]};
        return output;
    }

    /**
     * Returns the task from a input string that starts with "event".
     * 
     * @param taskString the input string that starts with "event"
     * @return           the task
     */
    public static String getTask(String taskString) {
        String[] arr = splitEventString(taskString);
        String task = arr[0];
        // remove the whitespace in front 
        return task.substring(0, task.length() - 1);
    }

    /**
     * Returns the start time from a input string that starts with "event".
     * 
     * @param taskString the input string that starts with "event"
     * @return           the start time
     */
    public String getStartTime(String taskString) {
        String[] arr = splitEventString(taskString);
        String startTime = arr[1];
        return startTime.substring(1, startTime.length() - 1);
    }

    /**
     * Returns the end time from a input string that starts with "event".
     * 
     * @param taskString the input string that starts with "event"
     * @return           the end time
     */
    public String getEndTime(String taskString) {
        String[] arr = splitEventString(taskString);
        String endTime = arr[2];
        return endTime.substring(1, endTime.length() - 1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
