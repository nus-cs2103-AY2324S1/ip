import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class Parser {
    public static Task parseTaskFromFileString(String lineFromFile) throws DukeException {
        Task task;
        String[] lineParts = lineFromFile.split(" \\| ");
        String taskType = lineParts[0];
        boolean isDone = lineParts[1].equals("X");
        String description = lineParts[2];
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by;
                if (lineParts.length == 4) {
                    by = lineParts[3];
                    task = new Deadline(description, by); // add by
                } else {
                    task = new Deadline(description);
                }
                break;
            case "E":
                String from;
                String to;
                if (lineParts.length == 5) {
                    from = lineParts[3];
                    to = lineParts[4];
                    task = new Event(description, from, to); // add from and to
                } else {
                    task = new Event(description);
                }
                break;
            default:
                // Line does not start with a task value (T,D,E), file corrupted, throw error
                throw new IllegalArgumentException("Invalid Tasks.Task Type");
        }
        if (isDone) task.markAsDone();
        return task;
    }

    public static String getCommand(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        String inputCommand = inputParts[0];
        if (!(inputCommand.equals("todo") || inputCommand.equals("deadline") || inputCommand.equals("event"))) {
            throw new InvalidCommandException();
        }
        return inputCommand;
    }

    public static String getTaskDesc(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        if (inputParts.length < 2) throw new NoDescriptionException();
        return inputParts[1];
    }

    public static int getTaskId(String inputString) throws DukeException {
        String[] inputParts = inputString.split(" ", 2);
        // throws a Exceptions.NoTaskFoundException if no task is found
        if (inputParts.length != 2) throw new NoTaskFoundException();
        try {
            int taskId = Integer.parseInt(inputParts[1].trim());
            return taskId - 1;
        } catch (NumberFormatException e) {
            // throws Exceptions.InvalidTaskException if the taskId is not an integer
            throw new InvalidTaskException();
        }
    }
}
