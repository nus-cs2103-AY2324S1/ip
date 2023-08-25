package duke;

import java.util.Scanner;

/**
 * Represents the parser of the chatbot.
 */
public class Parser {
    /** A scanner to read user input from the console. */
    public final static Scanner scanner = new Scanner(System.in);

    /**
     * Starts the chatbot parser.
     * <p>Prompts the user for input and processes commands until the chatbot should stop.
     */
    public static void start() {
        Duke.greet();

        boolean continueParsing = true;
        while (continueParsing) {
            String input = scanner.nextLine();
            try {
                continueParsing = parseCommand(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Parses the user's input and performs the corresponding action.
     *
     * @param input The user's input command.
     * @return {@code true} if parsing should continue, {@code false} if parsing should stop.
     * @throws IllegalArgumentException If the input command has invalid arguments.
     * @throws IndexOutOfBoundsException If the input command refers to an out-of-range index.
     */
    public static boolean parseCommand(String input) {
        // Split into command and rest
        String[] parts = input.split(" ", 2);
        final String command = parts[0];
        final String rest = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "bye": {
                Duke.exit();
                return false;
            }

            case "list": {
                Duke.list();
                break;
            }

            case "mark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.mark(index);
                break;
            }

            case "unmark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.unmark(index);
                break;
            }

            case "todo": {
                Duke.add(new ToDo(rest));
                break;
            }

            case "deadline": {
                try {
                    final String[] deadlineParts = rest.split(" /by ", 2);
                    final String name = deadlineParts[0];
                    final String endTime = deadlineParts[1];
                    Duke.add(new Deadline(name, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format. Usage: deadline <name> /by <time>");
                }
                break;
            }

            case "event": {
                try {
                    final String[] deadlineParts = rest.split(" /from ", 2);
                    final String name = deadlineParts[0];
                    final String deadline = deadlineParts[1];

                    final String[] startAndEndParts = deadline.split(" /to ", 2);
                    final String startTime = startAndEndParts[0];
                    final String endTime = startAndEndParts[1];

                    Duke.add(new Event(name, startTime, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format. Usage: event <name> /from <time> /to <time>");
                }
                break;
            }

            case "delete": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Task index is missing.");
                }
                int index = Integer.parseInt(rest);
                Duke.delete(index);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown command.");
            }
        }

        return true;
    }
}
