package miles.task;

import java.time.LocalDateTime;

import miles.MilesException;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private static String noDescErrorMsg = "Hold up... The description of a event cannot be empty.";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor to create a new event task when given a task string.
     * @param task  the task
     */
    public Event(String task) throws MilesException {
        // this constructor is for creating a task with the "event" command
        super(getTask(task));
        LocalDateTime t1 = this.convertToDateTime(this.getStartTime(task));
        LocalDateTime t2 = this.convertToDateTime(getEndTime(task));

        if (checkStartTimeBeforeEndTime(t1, t2)) {
            startTime = this.convertToDateTime(this.getStartTime(task));
            endTime = this.convertToDateTime(getEndTime(task));
        } else {
            throw new MilesException("Hold up... The start time of a event cannot be the same as or after the end"
                    + " time.");
        }
    }

    /**
     * Constructor to create a new event task when specifically given a start time and end time.
     * @param task      the task
     * @param startTime the start time
     * @param endTime   the end time
     */
    public Event(String task, String startTime, String endTime) throws MilesException {
        // this constructor is for loading the file
        super(task);
        LocalDateTime t1 = this.convertToDateTime(startTime);
        LocalDateTime t2 = this.convertToDateTime(endTime);

        if (checkStartTimeBeforeEndTime(t1, t2)) {
            this.startTime = t1;
            this.endTime = t2;
        } else {
            throw new MilesException("OOPS!!! The start time of a event cannot be after the end time.");
        }
    }

    /**
     * Splits a string into an array of 3 elements, the first element is the task, second is
     * the start time, third is the end time. Worth noting that the task, start time and end time
     * strings here are not trimmed yet (i.e. they still have leading or trailing whitespaces).
     * @param taskString the string that contains the task, start time and end time
     * @return           an array of 3 strings
     */
    public static String[] splitEventString(String taskString) throws MilesException {
        if (checkTaskNoDescription(taskString, "event")) {
            throw new MilesException(noDescErrorMsg);
        }

        String withoutCommand = taskString.substring(6);
        if (checkAllWhiteSpace(withoutCommand)) {
            throw new MilesException(noDescErrorMsg);
        }

        if (checkDuplicateParameter(withoutCommand, "/from")) {
            throw new MilesException("Invalid deadline format: cannot have more than 1 \"/from\".");
        }

        String[] eventParts = withoutCommand.split("/from");
        if (eventParts.length == 1) {
            throw new MilesException("Invalid event format: missing /from");
        }

        if (checkAllWhiteSpace(eventParts[0])) {
            throw new MilesException(noDescErrorMsg);
        }

        // need to split the 2nd element as it contains both the start and end time
        String timePart = eventParts[1];

        if (checkDuplicateParameter(timePart, "/to")) {
            throw new MilesException("Invalid event format: cannot have more than 1 \"/to\".");
        }

        String[] timeParts = timePart.split("/to");

        if (timeParts.length == 1) {
            throw new MilesException("Invalid event format: missing /to");
        }

        String[] output = {eventParts[0], timeParts[0], timeParts[1]};
        return output;
    }

    /**
     * Returns the task from a input string that starts with "event".
     * @param taskString the input string that starts with "event"
     * @return           the task
     */
    public static String getTask(String taskString) throws MilesException {
        String[] strings = splitEventString(taskString);
        assert strings.length == 3 : "The array should have 3 elements.";
        String task = strings[0];

        if (checkAllWhiteSpace(task)) {
            throw new MilesException(noDescErrorMsg);
        }

        return task.trim();
    }

    /**
     * Returns the start time from a input string that starts with "event".
     * @param taskString the input string that starts with "event"
     * @return           the start time
     */
    public String getStartTime(String taskString) throws MilesException {
        String[] strings = splitEventString(taskString);
        assert strings.length == 3 : "The array should have 3 elements.";
        String startTime = strings[1];

        if (checkAllWhiteSpace(startTime)) {
            throw new MilesException("Hold up... The start time of a event cannot be empty.");
        }

        return startTime.trim();
    }

    /**
     * Returns the end time from a input string that starts with "event".
     * @param taskString the input string that starts with "event"
     * @return           the end time
     */
    public String getEndTime(String taskString) throws MilesException {
        String[] strings = splitEventString(taskString);
        assert strings.length == 3 : "The array should have 3 elements.";
        String endTime = strings[2];

        if (checkAllWhiteSpace(endTime)) {
            throw new MilesException("Hold up... The end time of a event cannot be empty.");
        }

        return endTime.trim();
    }

    /**
     * Checks if the start time is before the end time before initialising the event task.
     * @param startTime  the start time
     * @param endTime    the end time
     * @return           true if the start time is before the end time, false otherwise
     */
    public boolean checkStartTimeBeforeEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    /**
     * Returns a string representing the start time to be displayed in the user interface.
     * @return the start time to be displayed in the UI
     */
    public String displayStartTime() {
        return this.displayTime(startTime);
    }

    /**
     * Returns a string representing the start time which would form a part of the string to be
     * saved in the text file.
     * @return string representing the start time to be saved in the text file
     */
    public String saveStartTime() {
        return this.saveTime(startTime);
    }

    /**
     * Returns a string representing the end time to be displayed in the user interface.
     * @return the end time to be displayed in the UI
     */
    public String displayEndTime() {
        return this.displayTime(endTime);
    }

    /**
     * Returns a string representing the end time which would form a part of the string to be saved
     * in the text file.
     * @return string representing the end time to be saved in the text file
     */
    public String saveEndTime() {
        return this.saveTime(endTime);
    }

    /**
     * Returns a string to be saved in the text file, specifically for an event.
     * @return a string to be saved in the text file
     */
    @Override
    public String saveStringToFile() {
        return "E" + super.saveStringToFile() + " | " + this.saveStartTime() + " | "
                + this.saveEndTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.displayStartTime() + " to: "
                + this.displayEndTime() + ")";
    }
}
