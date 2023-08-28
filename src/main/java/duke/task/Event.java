package duke.task;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static String noDescErrorMsg = "OOPS!!! The description of a event cannot be empty.";

    public Event(String task) {
        // this is for creating a task with the "event" command
        super(getTask(task));
        this.startTime = this.convertToDateTime(this.getStartTime(task));
        this.endTime = this.convertToDateTime(getEndTime(task));
    }

    public Event(String task, String startTime, String endTime) {
        // this is for loading the file
        super(task);
        this.startTime = this.convertToDateTime(startTime);
        this.endTime = this.convertToDateTime(endTime);
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

        if (checkAllWhiteSpace(arr1[0])) {
            throw new IllegalArgumentException(noDescErrorMsg);
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
        String[] strings = splitEventString(taskString);
        String task = strings[0];

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
        String[] strings = splitEventString(taskString);
        String startTime = strings[1];

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
        String[] strings = splitEventString(taskString);
        String endTime = strings[2];

        if (checkAllWhiteSpace(endTime)) {
            throw new IllegalArgumentException("OOPS!!! The end time of a event cannot be empty.");
        }

        return endTime.trim();
    }

    public String displayStartTime() {
        return this.displayTime(this.startTime);
    }

    public String saveStartTime() {
        return this.saveTime(this.startTime);
    }

    public String displayEndTime() {
        return this.displayTime(this.endTime);
    }

    public String saveEndTime() {
        return this.saveTime(this.endTime);
    }

    @Override
    public String saveStringToFile() {
        return "E" + super.saveStringToFile() + " | " + this.saveStartTime() + " | " + 
            this.saveEndTime(); 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.displayStartTime() + " to: " + 
            this.displayEndTime() + ")";
    }
}
