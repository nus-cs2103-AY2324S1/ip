public class Event extends Task {
    private String startTime;
    private String endTime;
    private static String noDescErrorMsg = "\u2639 OOPS!!! The description of a event cannot be empty.";

    public Event(String task) {
        // this is for creating a task with the "event" command
        super(getTask(task));
        this.startTime = getStartTime(task);
        this.endTime = getEndTime(task);
    }

    public Event(String task, String startTime, String endTime) {
        // this is for loading the file
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
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
        if (checkTaskNoDescription(taskString, "event")) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }

        String removeCmd = taskString.substring(6);
        if (checkAllWhiteSpace(removeCmd)) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }

        String[] arr1 = removeCmd.split("/from");
        if (arr1.length == 1) {
            throw new IllegalArgumentException("Invalid event format: missing /from");
        }
        // we still need to split the 2nd element because it contains both the start and end time
        String secondPart = arr1[1];
        String[] arr2 = secondPart.split("/to");

        if (arr2.length == 1) {
            throw new IllegalArgumentException("Invalid event format: missing /to");
        }

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

        if (checkAllWhiteSpace(task)) {
            throw new IllegalArgumentException(noDescErrorMsg);
        }
        // remove the whitespace in front 
        return task.trim();
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

        if (checkAllWhiteSpace(startTime)) {
            throw new IllegalArgumentException("OOPS!!! The start time of a event cannot be empty.");
        }

        return startTime.trim();
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

        if (checkAllWhiteSpace(endTime)) {
            throw new IllegalArgumentException("OOPS!!! The end time of a event cannot be empty.");
        }

        return endTime.trim();
    }

    @Override
    public String stringToSave() {
        return "E" + super.stringToSave() + " | " + this.startTime + " | " + this.endTime; 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
