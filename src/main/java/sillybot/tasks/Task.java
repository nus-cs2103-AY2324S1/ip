package sillybot.tasks;

import java.time.LocalDate;

import sillybot.DatesAndTimesFormatter;
import sillybot.exceptions.IncompleteInputException;
import sillybot.exceptions.InvalidInputException;

/**
 * Represents a Task object that can be a TodoTask, DeadlineTask or EventTask.
 */
public class Task {
    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     * Keeps track of the number of Tasks created.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        taskCount++;
    }

    /**
     * Returns the status icon of the Task.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the current count of tasks.
     *
     * @return the current count of tasks.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Decreases the taskCount by one.
     */
    public static void decreaseTaskCountByOne() {
        taskCount--;
    }

    /**
     * Sets the Task as done.
     * Prints a message to the user.png.
     *
     * @param task The Task to be set as done.
     * @return The message to the user.png.
     */
    public String markTaskAsDone(Task task) {
        this.isDone = true;
        String output = "Whoa... are you kidding me? You did that!?";

        return output + "\n" + task;
    }

    /**
     * Sets the Task as done.
     * Used only for Tasks read from the file to avoid printing the message to the user.png.
     */
    public void markTaskAsDoneFromFile() {
        this.isDone = true;
    }

    /**
     * Sets the Task as undone.
     * Prints a message to the user.png.
     *
     * @param task The Task to be set as undone.
     * @return The message to the user.png.
     */
    public String unmarkTaskAsUndone(Task task) {
        this.isDone = false;
        String output = "HAHHAA! I knew it! You won't be able to!";

        return output + "\n" + task;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the Task command contain valid TaskType.
     *
     * @param splittedCommands The command input by the user.png.
     * @return taskType The type of Task (TodoTask, DeadlineTask, EventTask).
     * @throws IncompleteInputException If the command is incomplete.
     * @throws InvalidInputException   If the command is invalid.
     */
    private static String getTaskType(String[] splittedCommands)
            throws IncompleteInputException,
            InvalidInputException {
        String taskType = splittedCommands[0];
        boolean isLengthInvalid = splittedCommands.length == 1;
        boolean isTaskTypeValid = taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event");

        if (isLengthInvalid) {
            if (isTaskTypeValid) {
                throw new IncompleteInputException("The description of a " + taskType + " cannot be empty.");
            }

            throw new InvalidInputException("That is some garbage input you have there.");
        }
        return taskType;
    }

    /**
     * Creates a TodoTask Task.
     *
     * @param taskDescription The description of the TodoTask Task.
     * @return The TodoTask Task.
     */
    private static TodoTask createTodoTask(String taskDescription) {
        return new TodoTask(taskDescription);
    }

    /**
     * Creates a DeadlineTask Task.
     *
     * @param taskDescription The description of the DeadlineTask Task.
     * @return The DeadlineTask Task.
     * @throws IncompleteInputException If the command is incomplete.
     */
    private static DeadlineTask createDeadlineTask(String taskDescription) throws IncompleteInputException {
        String[] deadlineSplit = taskDescription.split(" /by ");

        if (deadlineSplit.length == 1) {
            throw new IncompleteInputException("DeadlineTask what ah? Why leave it empty?");
        }

        String deadlineDescription = deadlineSplit[0];
        String deadlineBy = deadlineSplit[1];

        return new DeadlineTask(deadlineDescription, deadlineBy);
    }

    /**
     * Creates an EventTask Task.
     *
     * @param taskDescription The description of the EventTask Task.
     * @return The EventTask Task.
     * @throws IncompleteInputException If the command is incomplete.
     */
    private static EventTask createEventTask(String taskDescription) throws IncompleteInputException {
        String[] eventSplit = taskDescription.split(" /from ");

        if (eventSplit.length == 1) {
            throw new IncompleteInputException("EventTask what ah? Why leave empty?");
        }

        String eventDescription = eventSplit[0];
        String eventFrom = eventSplit[1].split(" /to ")[0];
        String eventTo = eventSplit[1].split(" /to ")[1];

        return new EventTask(eventDescription, eventFrom, eventTo);
    }

    /**
     * Returns the type of Task (TodoTask, DeadlineTask, EventTask).
     * Checks the user.png command and creates the appropriate Task.
     *
     * @param command The command input by the user.png.
     * @return The number of Tasks created.
     * @throws IncompleteInputException If the command is incomplete.
     * @throws InvalidInputException    If the command is invalid.
     */
    public static Task createTask(String command) throws IncompleteInputException, InvalidInputException {
        String[] splittedCommands = command.split(" ", 2);
        String taskType = getTaskType(splittedCommands);
        String taskDescription = splittedCommands[1];

        switch (taskType) {
        case "todo":
            return createTodoTask(taskDescription);
        case "deadline":
            return createDeadlineTask(taskDescription);
        case "event":
            return createEventTask(taskDescription);
        default:
            throw new InvalidInputException("That is some garbage input you have there.");
        }
    }

    /**
     * Creates a task based on the line in the file.
     *
     * @param taskLine Line in the file.
     * @return Task created.
     */
    public static Task createTaskFromFile(String taskLine) {
        char taskType = taskLine.charAt(1);
        char taskStatus = taskLine.charAt(5);
        String taskDescription = taskLine.substring(8);
        Task task;

        switch (taskType) {
        case 'T':
            task = new TodoTask(taskDescription);
            break;
        case 'D':
            String deadlineDescription = taskDescription.split(" \\(by: ")[0];
            String deadlineBy = taskDescription.split(" \\(by: ")[1].split("\\)")[0];
            task = new DeadlineTask(deadlineDescription, deadlineBy);
            break;
        case 'E':
            String eventDescription = taskDescription.split(" \\(from: ")[0];
            String eventFrom = taskDescription.split(" \\(from: ")[1].split(" to: ")[0];
            String eventTo = taskDescription.split(" \\(from: ")[1].split(" to: ")[1].split("\\)")[0];
            task = new EventTask(eventDescription, eventFrom, eventTo);
            break;
        default:
            System.out.println("Unknown task type. Returning null...");
            return null;
        }

        if (taskStatus == 'X') {
            task.markTaskAsDoneFromFile();
        }

        return task;
    }

    /**
     * Returns the date in LocalDate format.
     * Checks the date format and returns the date in LocalDate format.
     *
     * @param date The date to be parsed.
     * @return The date in LocalDate format.
     */
    public static LocalDate parseDate(String date) {
        for (DatesAndTimesFormatter formatter : DatesAndTimesFormatter.values()) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, formatter.formatter);
                assert parsedDate != null : "Parsed date cannot be null";
                return parsedDate;
            } catch (Exception e) {
                // Do nothing
            }
        }

        System.out.println("Unknown date format. Returning null...");
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
