package duke;

import java.util.Scanner;

/**
 * Represents the parser of the chatbot.
 */
public class Parser {
    public static String parseCommand(String input) {
        // Split into command and rest
        String[] parts = input.split(" ", 2);
        final String command = parts[0];
        final String rest = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "bye": {
            return Duke.exit();
        }

        case "deadline": {
            try {
                final String[] deadlineParts = rest.split(" /by ", 2);
                final String name = deadlineParts[0];
                final String endTime = deadlineParts[1];
                return Duke.add(new Deadline(name, endTime));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid format. Usage: deadline <name> /by <time>");
            }
        }

        case "delete": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return Duke.delete(index);
        }

        case "event": {
            try {
                final String[] deadlineParts = rest.split(" /from ", 2);
                final String name = deadlineParts[0];
                final String deadline = deadlineParts[1];

                final String[] startAndEndParts = deadline.split(" /to ", 2);
                final String startTime = startAndEndParts[0];
                final String endTime = startAndEndParts[1];

                return Duke.add(new Event(name, startTime, endTime));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Invalid format. Usage: event <name> /from <time> /to <time>");
            }
        }

        case "find": {
            return Duke.listFiltered(rest);
        }

        case "list": {
            return Duke.list();
        }

        case "mark": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return Duke.mark(index);
        }

        case "todo": {
            return Duke.add(new ToDo(rest));
        }

        case "unmark": {
            if (rest.isEmpty()) {
                throw new IllegalArgumentException("Task index is missing.");
            }
            int index = Integer.parseInt(rest);
            return Duke.unmark(index);
        }

        default: {
            throw new IllegalArgumentException("Unknown command.");
        }
        }
    }
}
