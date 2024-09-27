package sana;
import java.time.LocalDate;

/**
 * Represents the task inputted by the user.
 */
public abstract class Task {
    private static final int STATUS_INDEX = 4; // index of '1' or '0' which indicates status of task
    private static final int ARGUMENTS_START_INDEX = 8; // starting index of arguments

    // difference between '|' and the next non-whitespace line
    private static final int DIVIDER_AND_ARGUMENT_ID_DIFFERENCE = 2;
    private String arguments;
    private boolean isDone;

    /**
     * Creates an instance of task.
     *
     * @param arguments of task.
     */
    public Task(String arguments, boolean isDone) {
        this.arguments = arguments;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns status icon for task.
     *
     * @return X if task is done and whitespace if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task description.
     *
     * @return Task description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.arguments;
    }

    /**
     * Returns the formatted task string to be saved in the hard disk.
     *
     * @return formatted task string to be saved in the hard disk.
     */
    public String formatTask() {
        return " | " + (isDone ? 1 : 0) + " | " + arguments;
    }

    /**
     * Returns the Task equivalent of a given formatted string task loaded from file.
     *
     * @param formattedTask task string that has been formatted in a specific manner from the file.
     * @return task equivalence of the formatted task string.
     */
    public static Task getTask(String formattedTask) {

        char type = formattedTask.charAt(0);
        boolean isDone = (formattedTask.charAt(STATUS_INDEX) == '1' ? true : false);
        String arguments = formattedTask.substring(ARGUMENTS_START_INDEX);

        switch (type) {
        case 'T':
            return getTodo(arguments, isDone);
        case 'D':
            return getDeadline(arguments, isDone);
        case 'E':
            return getEvent(arguments, isDone);
        default:
            return null;
        }
    }

    /**
     * Updates the current task based on the provided arguments.
     *
     * @param arguments The new arguments for the task.
     * @return A new Task object representing the updated task, or null if the task type is unsupported.
     */
    public Task updateTask(String arguments) {
        if (this instanceof Todo) {
            return new Todo(arguments, isDone);
        }

        if (this instanceof Deadline) {
            String[] parsedArguments = arguments.split("/");
            String description = parsedArguments[0];

            String[] dueBy = parsedArguments[1].split(" ", 2);
            LocalDate dueDate = LocalDate.parse(dueBy[1]);
            return new Deadline(description, dueDate, isDone);
        }

        if (this instanceof Event) {
            String[] parsedArguments = arguments.split("/");
            String description = parsedArguments[0];
            String[] startDateArray = parsedArguments[1].split(" ", 2);
            String[] endDateArray = parsedArguments[2].split(" ", 2);

            String unparsedStartDateString = startDateArray[1];
            String parsedStartDateString = unparsedStartDateString.substring(0, unparsedStartDateString.length() - 1);

            LocalDate startDate = LocalDate.parse(parsedStartDateString);
            LocalDate endDate = LocalDate.parse(endDateArray[1]);
            return new Event(description, startDate, endDate, isDone);
        }
        return null;
    }

    /**
     * Creates a new Todo task.
     *
     * @param arguments The description of the todo task.
     * @param isDone    The status of the todo task (done or not done).
     * @return A new Todo task.
     */
    private static Task getTodo(String arguments, boolean isDone) {
        return new Todo(arguments, isDone);
    }

    /**
     * Creates a new Deadline task.
     *
     * @param arguments                The description and due date of the deadline task.
     * @param isDone                   The status of the deadline task (done or not done).
     * @return A new Deadline task.
     */
    private static Task getDeadline(String arguments, boolean isDone) {
        int descriptionEndIndex = arguments.indexOf("|");
        int dueDateStartIndex = descriptionEndIndex + DIVIDER_AND_ARGUMENT_ID_DIFFERENCE;

        String description = arguments.substring(0, descriptionEndIndex - 1);

        LocalDate dueDate = LocalDate.parse(arguments.substring(dueDateStartIndex));

        return new Deadline(description, dueDate, isDone);
    }

    /**
     * Creates a new Event task.
     *
     * @param arguments                The description, start date, and end date of the event task.
     * @param isDone                   The status of the event task (done or not done).
     * @return A new Event task.
     */
    private static Task getEvent(String arguments, boolean isDone) {
        int descriptionEndIndex = arguments.indexOf('|');
        int fromDateStartIndex = descriptionEndIndex + DIVIDER_AND_ARGUMENT_ID_DIFFERENCE;
        int fromDateEndIndex = arguments.indexOf('|', descriptionEndIndex + 1);
        int toDateStartIndex = fromDateEndIndex + DIVIDER_AND_ARGUMENT_ID_DIFFERENCE;

        String description = arguments.substring(0, descriptionEndIndex - 1);

        LocalDate fromDate = LocalDate.parse(arguments.substring(fromDateStartIndex, fromDateEndIndex - 1));
        LocalDate toDate = LocalDate.parse(arguments.substring(toDateStartIndex));

        return new Event(description, fromDate, toDate, isDone);
    }

    /**
     * Indicates whether some other object is "equal to" this task.
     *
     * This method compares the string representations of two Task objects to determine
     * if they are equal. The comparison is based on the equality of the string
     * representations of the tasks.
     *
     * @param obj the reference object with which to compare.
     * @return true if this task is equal to the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task task = (Task) obj;

            if (this == null || task == null) {
                return false;
            }

            return this.toString().equals(task.toString());
        }
        return false;
    }
}
