package duke.task;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import duke.Duke;

abstract class Task {
    protected boolean isDone = false;
    protected String description;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static Task createTask(String task) throws Duke.WrongCommandException, Duke.WrongFormatException {
        TaskType taskType = getTaskType(task);
        if (taskType == null) {
            throw new Duke.WrongCommandException("Whopsie daisies! I don't understand that command!");
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

    public static Task loadTask(String fileTask) throws Duke.WrongFormatException, Duke.InvalidFileException {
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
            throw new Duke.InvalidFileException("File is corrupted!");
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

    protected abstract String getTaskTypeString();

    protected abstract String getDescription(String input);

    protected abstract String saveToFileString();

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
