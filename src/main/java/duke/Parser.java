package duke;

/**
 * Represents the parser of the chatbot.
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input and returns an executable command based on the input.
     *
     * @param input The user's input string to be parsed.
     * @return An Executable representing the parsed command.
     * @throws IllegalArgumentException If the input is not a valid command or is missing required parameters.
     */
    public Executable parseCommand(String input) {
        // Split into command and rest
        String[] parts = input.split(" ", 2);
        final String command = parts[0];
        final String rest = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "deadline": {
            try {
                final String[] deadlineParts = rest.split(" /by ", 2);
                final String name = deadlineParts[0];
                final String endTime = deadlineParts[1];

                return new DeadlineAdder(tasks, name, endTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid format. Usage: deadline <name> /by <time>");
            }
        }

        case "delete": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return new TaskDeleter(tasks, index);
        }

        case "event": {
            try {
                final String[] deadlineParts = rest.split(" /from ", 2);
                final String name = deadlineParts[0];
                final String deadline = deadlineParts[1];

                final String[] startAndEndParts = deadline.split(" /to ", 2);
                final String startTime = startAndEndParts[0];
                final String endTime = startAndEndParts[1];

                return new EventAdder(tasks, name, startTime, endTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid format. Usage: event <name> /from <time> /to <time>");
            }
        }

        case "find": {
            return new Finder(tasks, rest);
        }

        case "list": {
            return new Lister(tasks);
        }

        case "mark": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return new TaskMarker(tasks, index);
        }

        case "todo": {
            return new ToDoAdder(tasks, rest);
        }

        case "unmark": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return new TaskUnmarker(tasks, index);
        }

        default: {
            throw new IllegalArgumentException("Unknown command.");
        }
        }
    }
}
