package duke.tasks;

import java.time.LocalDate;

import duke.DatesAndTimesFormatter;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;

/**
 * Represents a Task object that can be a Todo, Deadline or Event.
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
        this.isDone = false;
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
     * Prints a message to the user.
     *
     * @param task The Task to be set as done.
     * @return The message to the user.
     */
    public String setAsDone(Task task) {
        this.isDone = true;
        System.out.println("Whoa... are you kidding me? You did that!?" + "\n" + task);
        return "Whoa... are you kidding me? You did that!?" + "\n" + task;
    }

    /**
     * Sets the Task as done.
     * Used only for Tasks read from the file to avoid printing the message to the user.
     */
    public void setAsDoneFromFile() {
        this.isDone = true;
    }

    /**
     * Sets the Task as undone.
     * Prints a message to the user.
     *
     * @param task The Task to be set as undone.
     * @return The message to the user.
     */
    public String setAsUndone(Task task) {
        this.isDone = false;
        System.out.println("HAHHAA! I knew it! You won't be able to!" + "\n" + task);
        return "HAHHAA! I knew it! You won't be able to!" + "\n" + task;
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
     * Returns the type of Task (Todo, Deadline, Event).
     * Checks the user command and creates the appropriate Task.
     *
     * @param command The command input by the user.
     * @return The number of Tasks created.
     * @throws IncompleteInputException If the command is incomplete.
     * @throws InvalidInputException    If the command is invalid.
     */
    public static Task createTask(String command) throws IncompleteInputException, InvalidInputException {
        String[] splittedCommand = command.split(" ", 2);
        String taskType = splittedCommand[0];
        if (splittedCommand.length == 1) {
            if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
                throw new IncompleteInputException("The description of a " + taskType + " cannot be empty.");
            } else {
                throw new InvalidInputException("That is some garbage input you have there.");
            }
        } else {
            String taskDescription = splittedCommand[1];
            switch (taskType) {
            case "todo":
                return new Todo(taskDescription);
            case "deadline":
                String[] deadlineSplit = taskDescription.split(" /by ");
                if (deadlineSplit.length == 1) {
                    throw new IncompleteInputException("Deadline what ah? Why leave empty?");
                }
                String deadlineDescription = deadlineSplit[0];
                String deadlineBy = deadlineSplit[1];
                return new Deadline(deadlineDescription, deadlineBy);
            case "event":
                String[] eventSplit = taskDescription.split(" /from ");
                if (eventSplit.length == 1) {
                    throw new IncompleteInputException("Event what ah? Why leave empty?");
                }
                String eventDescription = eventSplit[0];
                String eventFrom = eventSplit[1].split(" /to ")[0];
                String eventTo = eventSplit[1].split(" /to ")[1];
                return new Event(eventDescription, eventFrom, eventTo);
            default:
                throw new InvalidInputException("That is some garbage input you have there.");
            }
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
            task = new Todo(taskDescription);
            break;
        case 'D':
            String deadlineDescription = taskDescription.split(" \\(by: ")[0];
            String deadlineBy = taskDescription.split(" \\(by: ")[1].split("\\)")[0];
            task = new Deadline(deadlineDescription, deadlineBy);
            break;
        case 'E':
            String eventDescription = taskDescription.split(" \\(from: ")[0];
            String eventFrom = taskDescription.split(" \\(from: ")[1].split(" to: ")[0];
            String eventTo = taskDescription.split(" \\(from: ")[1].split(" to: ")[1].split("\\)")[0];
            task = new Event(eventDescription, eventFrom, eventTo);
            break;
        default:
            System.out.println("Unknown task type. Returning null...");
            return null;
        }
        if (taskStatus == 'X') {
            task.setAsDoneFromFile();
        }
        return task;
    }

    /**
     * Returns the date in LocalDate format.
     * Checks the date format and returns the date in LocalDate format.
     *
     * @param date The date to be parsed.
     */
    public static LocalDate parseDate(String date) {
        for (DatesAndTimesFormatter formatter : DatesAndTimesFormatter.values()) {
            try {
                return LocalDate.parse(date, formatter.formatter);
            } catch (Exception e) {
                // Do nothing
            }
        }
        System.out.println("Unknown date format. Returning null...");
        return null;
    }

    @Override
    public String toString() {
        return "|" + this.getStatusIcon() + "| " + this.getDescription();
    }
}
