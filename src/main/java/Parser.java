import Exceptions.*;
import Exceptions.InvalidCommandException; // warning is shown in javadocs if not imported
import Exceptions.InvalidTaskException; // warning is shown in javadocs if not imported
import Exceptions.NoDescriptionException; // warning is shown in javadocs if not imported
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

/**
 * Parser that helps with parsing user inputs as Strings to various
 * Objects and types.
 */
public class Parser {

    /**
     * Parses the given line from file as a String and
     * returns a Task object.
     *
     * @param lineFromFile The given line from the file.
     * @return task The Task object created by the line after parsing.
     * @throws IllegalArgumentException If the line is invalid.
     */
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

    /**
     * Parses the user input and returns the command specified by the user.
     *
     * @param userInput The String which the user has typed.
     * @return inputCommand The command that the user has entered.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static String getCommand(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        String inputCommand = inputParts[0];
        if (!(inputCommand.equals("todo") || inputCommand.equals("deadline") || inputCommand.equals("event"))) {
            throw new InvalidCommandException();
        }
        return inputCommand;
    }

    /**
     * Parses the input given by the user and returns the task description.
     *
     * @param userInput The String which the user has typed.
     * @return The description of the task entered by the user.
     * @throws NoDescriptionException If no description is found.
     */
    public static String getTaskDesc(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        if (inputParts.length < 2) throw new NoDescriptionException();
        // Check if description is empty
        if (inputParts[1].trim().equals("")) throw new NoDescriptionException();
        return inputParts[1].trim();
    }

    /**
     * Parses the input given by the user and returns the task id.
     *
     * @param inputString The String which the user has typed.
     * @return The id of the task (1-indexed).
     * @throws InvalidTaskException If the task id is in an invalid format e.g a String.
     */
    public static int getTaskId(String inputString) throws DukeException {
        String[] inputParts = inputString.split(" ", 2);
        if (inputParts.length != 2) throw new NoTaskFoundException();
        try {
            int taskId = Integer.parseInt(inputParts[1].trim());
            return taskId - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }
}
