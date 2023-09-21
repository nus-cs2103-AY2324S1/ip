package duke.task;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidFileException;
import duke.exception.DukeWrongCommandFormatException;

/**
 * Abstract implementation of {@code Task}
 */
public abstract class Task implements Comparable<Task> {
    protected boolean isDone = false;
    protected String description;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Creates a new {@code Task}
     * @param task String representation of a task
     * @return Task
     * @throws DukeInvalidCommandException if the command is invalid
     * @throws DukeWrongCommandFormatException if the format is invalid
     */
    public static Task createTask(String task)
            throws DukeInvalidCommandException, DukeWrongCommandFormatException {
        TaskType taskType = getTaskType(task);
        if (taskType == null) {
            throw new DukeInvalidCommandException("Whopsie daisies! I don't understand that command!");
        }

        switch (taskType) {
        case TODO:
            return new TodoTask(task);
        case DEADLINE:
            return new DeadlineTask(task);
        case EVENT:
            return new EventTask(task);
        default:
            return null;
        }
    }

    /**
     * Loads a task from a file
     * @param fileTask String representation of a task
     * @return Task
     * @throws DukeWrongCommandFormatException if the file is corrupted
     * @throws DukeInvalidFileException if the file is corrupted
     */
    public static Task loadTask(String fileTask)
            throws DukeWrongCommandFormatException, DukeInvalidFileException {
        String[] taskDetails = fileTask.split(" \\| ");
        try {
            TaskType taskType = TaskType.valueOf(taskDetails[0]);
            boolean isDone = taskDetails[1].equals("1");
            String description = taskDetails[2];

            switch (taskType) {
            case TODO:
                return new TodoTask(isDone, description);
            case DEADLINE:
                return new DeadlineTask(isDone, description, taskDetails[3]);
            case EVENT:
                return new EventTask(isDone, description, taskDetails[3], taskDetails[4]);
            default:
                return null;
            }
        } catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
            throw new DukeInvalidFileException("File is corrupted!");
        }
    }

    protected static TaskType getTaskType(String input) {
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        }

        if (input.startsWith("deadline")) {
            return TaskType.DEADLINE;
        }

        if (input.startsWith("event")) {
            return TaskType.EVENT;
        }

        return null;
    }

    protected static String squareBracketWrapper(String input) {
        return "[" + input + "]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public int compareTo(Task other) {
        // Precedence of tasks: Sort by type
        int typeComparison = this.getTaskTypeString().compareTo(other.getTaskTypeString());
        if (typeComparison != 0) {
            return typeComparison;
        }

        // Sort by done status
        int doneComparison = Boolean.compare(this.isDone, other.isDone);
        if (doneComparison != 0) {
            return doneComparison;
        }

        // Sort by description
        return this.description.compareTo(other.description);
    }

    protected abstract String getTaskTypeString();

    protected abstract String getDescription(String input);

    protected abstract String saveToFileString();

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
