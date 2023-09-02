package trackerbot.utils;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import trackerbot.command.Command;
import trackerbot.command.CommandType;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.Deadline;
import trackerbot.task.Event;
import trackerbot.task.Task;
import trackerbot.task.Todo;

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
    public static Command parseCommand(String input) {
        Scanner scanner = new Scanner(input);
        String keyword;
        String rest;
        // if the input is empty, return the unknown keyword with an empty description.
        if (!scanner.hasNext()) {
            keyword = "";
        } else {
            keyword = scanner.next();
        }

        if (keyword.equals("") || !scanner.hasNextLine()) {
            rest = "";
        } else {
            rest = scanner.nextLine().trim();
        }

        return Command.of(keyword, rest);
    }

    public static Task parseAdd(CommandType type, String commandField) throws TrackerBotException {
        Task newTask;
        String[] segments;
        try {
            switch (type) {
            case TODO:
                if (commandField.equals("")) {
                    throw new TrackerBotException("Cannot track task without description.");
                }
                newTask = new Todo(commandField.trim());
                break;
            case DEADLINE:
                if (!commandField.matches("^.+ /by .+")) {
                    throw new TrackerBotException("Improper format: deadline [description] /by [end-date]");
                }

                segments = commandField.split("/by");
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
                if (!commandField.matches("^.+ /from .+ /to .+")) {
                    throw new TrackerBotException(
                            "Improper format: event [description] /from [start-date] /to [end-date]");
                }

                segments = commandField.split("/from|/to");
                if (segments.length > 3) {
                    throw new TrackerBotException(
                            "Too many flags: event [description] /from [start-date] /to [end-date]");
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
                throw new IllegalStateException("Uncaught CommandType: " + type.getKeyword());
            }
            return newTask;
        } catch (DateTimeParseException e) {
            throw new TrackerBotException("Additional Date Fields should be in the format DD/MM(/YYYY)( HHmm).");
        }
    }
}
