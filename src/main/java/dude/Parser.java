package dude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dude.command.*;

/**
 * Represents a parser that parses user input and deals with making sense of the user command.
 */
public class Parser {
    private static final DateTimeFormatter DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses the user's input and returns the corresponding command.
     *
     * @param fullCommand The full user command input.
     * @return A command object representing the user's intent.
     */
    public static Command parse(String fullCommand) {
        String[] commandDetails = fullCommand.split(" ", 2);
        String commandType = commandDetails[0];
        Command c;

        if (commandType.equals("bye")) {
            c = new ExitCommand();
        } else if (commandType.equals("list")) {
            c = new ListCommand();
        } else if (commandType.equals("mark")) {
            int taskIndex = Integer.parseInt(commandDetails[1]) - 1;
            c = new MarkCommand(taskIndex);
        } else if (commandType.equals("unmark")) {
            int taskIndex = Integer.parseInt(commandDetails[1]) - 1;
            c = new UnmarkCommand(taskIndex);
        } else if (commandType.equals("delete")) {
            String[] details = commandDetails[1].split(" ");
            int index = Integer.parseInt(details[1]) - 1;

            if (fullCommand.matches("(.+)/t(.+)")) {
                c = new DeleteTaskCommand(index);
            } else if (fullCommand.matches("(.+)/n(.+)")) {
                c = new DeleteNoteCommand(index);
            } else {
                c = new UnknownCommand(fullCommand);
            }

        } else if (commandType.equals("todo")) {

            if (commandDetails.length == 1) {
                return new UnknownCommand(fullCommand); // error
                // System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }

            String taskDescription = commandDetails[1].trim();
            c = new AddToDoCommand(taskDescription);

        } else if (commandType.equals("deadline")) {

            if (commandDetails.length == 1) {
                return new UnknownCommand(fullCommand); // error
                // System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            } else if (!fullCommand.matches("(.+)/by(.+)")) {
                return new UnknownCommand(fullCommand);
                // wrong format
            }

            String[] taskDetails = commandDetails[1].split("\\s/by\\s");
            String taskDescription = taskDetails[0].trim();
            LocalDateTime byDateTime = LocalDateTime.parse(taskDetails[1].trim(), DATETIME_FORMATTER);
            c = new AddDeadlineCommand(taskDescription, byDateTime);

        } else if (commandType.equals("event")) {

            if (commandDetails.length == 1) {
                return new UnknownCommand(fullCommand); // error
                // System.out.println("OOPS!!! The description of an event cannot be empty.");
            } else if (!fullCommand.matches("(.+)/from(.+)/to(.+)")) {
                return new UnknownCommand(fullCommand);
                // wrong format
            }

            String[] taskDetails = commandDetails[1].split("\\s/from\\s|\\s/to\\s");
            String taskDescription = taskDetails[0].trim();
            LocalDateTime fromDateTime = LocalDateTime.parse(taskDetails[1].trim(), DATETIME_FORMATTER);
            LocalDateTime toDateTime = LocalDateTime.parse(taskDetails[2].trim(), DATETIME_FORMATTER);
            c = new AddEventCommand(taskDescription, fromDateTime, toDateTime);

        } else if (commandType.equals("note")) {

            if (commandDetails.length == 1) {
                return new UnknownCommand(fullCommand); // error
                // System.out.println("OOPS!!! The description of a note cannot be empty.");
            }

            String noteDescription = commandDetails[1].trim();
            c = new AddNoteCommand(noteDescription);

        } else if (commandType.equals("find")) {

            if (commandDetails.length == 1) {
                return new UnknownCommand(fullCommand); // error
                // System.out.println("OOPS!!! The description of a find cannot be empty.");
            }

            String searchKeywords = commandDetails[1].trim();
            c = new FindCommand(searchKeywords);

        } else {
            c = new UnknownCommand(fullCommand);
        }
        return c;
    }
}
