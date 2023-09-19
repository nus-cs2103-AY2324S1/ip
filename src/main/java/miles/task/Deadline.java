package miles.task;

import java.time.LocalDateTime;

import miles.MilesException;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private static String NO_DESC_ERROR_MSG = "OOPS!!! The description of a deadline cannot be empty.";
    private LocalDateTime deadline;

    /**
     * Constructor to create a new deadline task when given a task string.
     * @param task            the task
     * @throws MilesException when the task string is not in the right format
     */
    public Deadline(String task) throws MilesException {
        super(getTask(task));
        deadline = this.convertToDateTime(this.getDeadline(task));
    }

    /**
     * Constructor to create a new deadline task when specifically given a deadline string.
     * @param task    the task
     * @param deadline the deadline
     */
    public Deadline(String task, String deadline) throws MilesException {
        super(task);
        this.deadline = this.convertToDateTime(deadline);
    }

    /**
     * Returns an array of 2 elements, the first element is the task, the second is the deadline.
     * Worth noting that the task and deadline strings here still have whitespaces that need to be
     * trimmed for use.
     * @param taskString      the string that contains the task and deadline
     * @return                an array of 2 strings
     * @throws MilesException when the task string is not in the right format
     */
    public static String[] splitDeadlineString(String taskString) throws MilesException {
        if (checkTaskNoDescription(taskString, "deadline")) {
            throw new MilesException(NO_DESC_ERROR_MSG);
        }

        // removes "deadline "  from the task string
        String withoutCommand = taskString.substring(9);
        if (checkAllWhiteSpace(withoutCommand)) {
            throw new MilesException(NO_DESC_ERROR_MSG);
        }

        if (checkDuplicateParameter(withoutCommand, "/by")) {
            throw new MilesException("Invalid deadline format: cannot have more than 1 \"/by\".");
        }

        String[] strings = withoutCommand.split("/by");
        if (strings.length == 1) {
            throw new MilesException("Invalid deadline format: missing /by");
        }

        return strings;
    }

    /**
     * Returns the task from a input string that starts with "deadline".
     * @param taskString      the input string that starts with "deadline"
     * @return                the task
     * @throws MilesException when the task string has no description
     */
    public static String getTask(String taskString) throws MilesException {
        String[] strings = splitDeadlineString(taskString);
        assert strings.length == 2 : "The array should have 2 elements.";
        String task = strings[0];

        if (checkAllWhiteSpace(task)) {
            throw new MilesException(NO_DESC_ERROR_MSG);
        }

        return task.trim();
    }

    /**
     * Returns the deadline from a input string that starts with "deadline".
     * @param taskString      the input string that starts with "deadline"
     * @return                the deadline
     * @throws MilesException when the deadline string has no description
     */
    public String getDeadline(String taskString) throws MilesException {
        String[] strings = splitDeadlineString(taskString);
        assert strings.length == 2 : "The array should have 2 elements.";
        String deadline = strings[1];

        if (checkAllWhiteSpace(deadline)) {
            throw new MilesException("OOPS!!! The deadline of a deadline cannot be empty.");
        }

        return deadline.trim();
    }

    // /**
    //  * Returns the number of "/by" in a string. It is used for error checking.
    //  * 
    //  * @param taskString the string to be checked
    //  * @return           the number of "/by" in the string
    //  */
    // public static int countNumberOfBys(String taskString) {
    //     int count = 0;
    //     int index = 0;
    //     String substring = "/by";

    //     while (index != -1) {
    //         index = taskString.indexOf(substring, index);
    //         if (index != -1) {
    //             count += 1;
    //             index += substring.length();
    //         }
    //     }

    //     return count;
    // }

    /**
     * Returns a string representing the deadline to be displayed in the user interface.
     * @return the deadline to be displayed in the UI
     */
    public String displayDeadline() {
        return this.displayTime(this.deadline);
    }

    /**
     * Returns a string representing the deadline which would form a part of the string to be saved
     * in the text file.
     * @return string representing the deadline to be saved in the text file
     */
    public String saveDeadline() {
        return this.saveTime(this.deadline);
    }

    /**
     * Returns a string to be saved in the text file, specifically for an deadline.
     * @return a string to be saved in the text file
     */
    @Override
    public String saveStringToFile() {
        return "D" + super.saveStringToFile() + " | " + this.saveDeadline();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.displayDeadline() + ")";
    }
}
