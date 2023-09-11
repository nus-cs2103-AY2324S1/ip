package duke;

/**
 * Deadline encapsulates task that need to be done before a specific date or time
 */
public class Deadline extends Task {
    public static final int NAME_OFFSET = 9;
    private String taskName;
    private DateTime dateTime;

    /**
     * Constructor for creating a task
     *
     * @param taskName name of task.
     */
    public Deadline(String taskName, DateTime dateTime) {
        super(taskName);
        this.taskName = taskName;
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
        this.dateTime = dateTime;
    }

    /**
     * taskValidator does nothing if there are no wrong inputs but throws a WrongInputException
     * if inputs are invalid
     * @param input the user's string input
     * @throws WrongInputException which informs the user of the error and actions to take
     */
    public static void taskValidator(String input) throws WrongInputException {
        String[] segementedString = input.split(" /by ");
        if (segementedString.length < 2) {
            throw new WrongInputException("/by command is required",
                    "Use the /by command using 'deadline <name> /by <time>");
        }
        String deadline = segementedString[1];
        // Test whether a deadline's input has a non-blank name
        try {
            String taskNameDeadline = segementedString[0].substring(NAME_OFFSET);
            if (taskNameDeadline.trim().isEmpty()) {
                throw new WrongInputException("Task name cannot be blank",
                        "Enter a non-blank deadline task name");
            } else if (deadline.trim().isEmpty()) {
                throw new WrongInputException("for /by <time>, time cannot be blank",
                        "Please input valid text that are not space");
            } else if (!DateTimeParser.isValidDateTime(deadline)) {
                throw new WrongInputException("Invalid date and time format",
                        DateTimeParser.getValidDateTimeFormat());
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new WrongInputException("Task name cannot be blank",
                    "Enter a non-blank deadline task name");
        }
    }

    /**
     * Creates a Deadline task from the user's input
     * @param input the user's input
     * @return  a Deadline task
     * @throws WrongInputException if the input is invalid
     */
    public static Task createTaskFromInput(String input) throws WrongInputException {
        String[] segementedString = input.split(" /by ");
        String deadline = segementedString[1];
        String taskNameDeadline = segementedString[0].substring(NAME_OFFSET);
        DateTime deadlineDateTime = DateTime.createDateTime(deadline);
        Task deadlineTask = new Deadline(taskNameDeadline, deadlineDateTime);
        return deadlineTask;
    }
    /**
     * Checks the type of task, which is event
     * @return  the type of task as a string
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Converts a Deadline task into a string that can be saved
     * @return  a string that can be saved
     */
    @Override
    public String convertToSaveFormat() {
        return "D" + Storage.FILESEPERATORCHARACTER + this.isDone() + Storage.FILESEPERATORCHARACTER + this.taskName
                + Storage.FILESEPERATORCHARACTER + this.dateTime.toString()
                + Storage.FILESEPERATORCHARACTER + this.saveTagFormat();
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] "
                + this.taskName + " (by: " + this.dateTime + ")";
    }

}
