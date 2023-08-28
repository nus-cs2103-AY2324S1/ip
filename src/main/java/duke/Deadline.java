package duke;

/**
 * Deadline encapsulates task that need to be done before a specific date or time
 */
public class Deadline extends Task {
    private String taskName;
    private String deadline;
    private DateTime dateTime;

    /**
     * Constructor for creating a task
     *
     * @param taskName name of task.
     */
    public Deadline(String taskName, DateTime dateTime) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = dateTime.getDateTime();
        this.dateTime = dateTime;
    }

    /**
     * Constructor for creating a task based on whether its done or not
     * @param taskName  name of task.
     * @param isDone    whether the task is done or not
     * @param dateTime the date and time of the deadline stored in a DateTime object
     */
    public Deadline(String taskName, boolean isDone, DateTime dateTime) {
        super(taskName);
        if (isDone) {
            super.quietlyCompleteTask();
        }
        this.taskName = taskName;
        this.deadline = dateTime.getDateTime();
        this.dateTime = dateTime;

    }

    /**
     * taskValidator does nothing if there are no wrong inputs but throws a WrongInputException
     * if inputs are invalid
     * @param input the user's string input
     * @throws WrongInputTask which informs the user of the error and actions to take
     */
    public static void taskValidator(String input) throws WrongInputTask {
        String[] segementedString = input.split(" /by ");
        if (segementedString.length < 2) {
            throw new WrongInputTask("/by command is required",
                    "Use the /by command using 'deadline <name> /by <time>");
        } else {
            String deadline = segementedString[1];
            try {
                String taskNameDeadline = segementedString[0].substring(Parser.DEADLINEOFFSET);
            } catch (StringIndexOutOfBoundsException e) {
                throw new WrongInputTask("Task name cannot be blank",
                        "Enter a non-blank deadline task name");
            }

            String taskNameDeadline = segementedString[0].substring(Parser.DEADLINEOFFSET);
            if (taskNameDeadline.trim().isEmpty()) {
                throw new WrongInputTask("Task name cannot be blank",
                        "Enter a non-blank deadline task name");
            } else if (deadline.trim().isEmpty()) {
                throw new WrongInputTask("for /by <time>, time cannot be blank",
                        "Please input valid text that are not space");
            } else if (!DateTimeParser.isValidDateTime(deadline)) {
                throw new WrongInputTask("Invalid date and time format",
                        DateTimeParser.getValidDateTimeFormat());
            }
        }
    }

    /**
     * Converts a Deadline task into a string that can be saved
     * @return  a string that can be saved
     */
    @Override
    public String convertToSaveFormat() {
        return "D" + Storage.FILESEPERATORCHARACTER + this.isDone() + Storage.FILESEPERATORCHARACTER + this.taskName
                + Storage.FILESEPERATORCHARACTER + this.dateTime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (by: " + this.dateTime + ")";
    }

}
