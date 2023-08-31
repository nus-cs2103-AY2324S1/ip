import ip.utils.Pair;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    /**
     * Helper function. Splits the console input string into the invoking keyword
     * and its description after the invoking keyword. <br>
     * If no description exists after the keyword, an empty string is returned in the
     * second half of the Pair structure.
     *
     * @param input The unmodified console string that the user inputs.
     * @return A Pair&lt;Command, String&gt; object containing the enum and description.
     */
    public static Pair<CommandType, String> parseCommand(String input) {
        Scanner scanner = new Scanner(input);
        // if the input is empty, return the unknown keyword with an empty description.
        if (!scanner.hasNext()) {
            scanner.close();
            return new Pair<>(CommandType.UNKNOWN, "");
        }

        String keyword = scanner.next();
        CommandType first = CommandType.UNKNOWN;

        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.getKeyword())) {
                first = command;
                break;
            }
        }

        if (first.equals(CommandType.UNKNOWN) || !scanner.hasNextLine()) {
            scanner.close();
            return new Pair<>(first, "");
        }

        String second = scanner.nextLine().trim();
        scanner.close();
        return new Pair<>(first, second);
    }

    public static Task parseAdd(Pair<CommandType, String> input) throws TrackerBotException {
        Task newTask;
        String[] segments;
        try {
            switch (input.getFirst()) {
            case TODO:
                if (input.getSecond().equals("")) {
                    throw new TrackerBotException("Cannot track task without description.");
                }
                newTask = new Todo(input.getSecond());
                break;
            case DEADLINE:
                if (!input.getSecond().matches("^.+ /by .+")) {
                    throw new TrackerBotException("Improper format: deadline [description] /by [end-date]");
                }

                segments = input.getSecond().split("/by");
                if (segments.length > 2) {
                    throw new TrackerBotException("Too many flags: deadline [description] /by [end-date]");
                }

                if (segments[0].trim().equals("")) {
                    throw new TrackerBotException("Cannot track task without description.");
                }
                if (segments[1].trim().equals("")) {
                    throw new TrackerBotException("Empty /by flag.");
                }

                newTask = new Deadline(segments[0].trim(), segments[1].trim());
                break;
            case EVENT:
                // this will check for the standard format, and will also guarantee that segment length is min 3.
                if (!input.getSecond().matches("^.+ /from .+ /to .+")) {
                    throw new TrackerBotException(
                            "Improper format: event [description] /from [start-date] /to [end-date]");
                }

                segments = input.getSecond().split("/from|/to");
                if (segments.length > 3) {
                    throw new TrackerBotException("Too many flags: event [description] /from [start-date] /to [end-date]");
                }

                if (segments[0].trim().equals("")) {
                    throw new TrackerBotException("Cannot track task without description");
                }
                if (segments[1].trim().equals("")) {
                    throw new TrackerBotException("Empty /from flag.");
                }
                if (segments[2].trim().equals("")) {
                    throw new TrackerBotException("Empty /to flag.");
                }

                newTask = new Event(segments[0].trim(), segments[1].trim(), segments[2].trim());
                break;
            default:
                throw new IllegalStateException("Uncaught CommandType: " + input.getFirst().getKeyword());
            }
            return newTask;
        } catch (DateTimeParseException e) {
            throw new TrackerBotException("Additional Date Fields should be in the format DD/MM(/YYYY)( HHmm).");
        }
    }
}
