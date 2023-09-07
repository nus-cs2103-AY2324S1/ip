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
        // Split into command and restOfCommand
        String[] words = input.split(" ", 2);
        final String command = words[0];
        final String restOfCommand = words.length > 1 ? words[1] : "";

        switch (command) {
        case "d":
        case "deadline": {
            try {
                final String[] deadlineParts = restOfCommand.split(" /by ", 2);
                final String name = deadlineParts[0];
                final String endTime = deadlineParts[1];

                return new DeadlineAdder(tasks, name, endTime);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid format. Usage: deadline <name> /by <time>");
            }
        }

        case "rm":
        case "delete": {
            if (restOfCommand.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(restOfCommand);
            return new TaskDeleter(tasks, index);
        }

        case "e":
        case "event": {
            try {
                final String[] deadlineParts = restOfCommand.split(" /from ", 2);
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

        case "f":
        case "find": {
            return new Finder(tasks, restOfCommand);
        }

        case "ls":
        case "list": {
            return new Lister(tasks);
        }

        case "m":
        case "mark": {
            if (restOfCommand.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(restOfCommand);
            assert index >= 0 : "Parsing failed; value is not a non-negative integer.";
            return new TaskMarker(tasks, index);
        }

        case "t":
        case "todo": {
            return new ToDoAdder(tasks, restOfCommand);
        }

        case "um":
        case "unmark": {
            if (restOfCommand.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(restOfCommand);
            assert index >= 0 : "Parsing failed; value is not a non-negative integer.";
            return new TaskUnmarker(tasks, index);
        }

        default: {
            throw new IllegalArgumentException("Unknown command.");
        }
        }
    }
}
