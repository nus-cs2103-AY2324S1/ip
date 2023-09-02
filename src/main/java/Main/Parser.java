package Main;

import Command.ByeCommand;
import Command.Command;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.ToDoCommand;
import Command.EventCommand;
import Command.DeadlineCommand;
import Command.DeleteCommand;

import Exception.DukeException;

import java.util.Arrays;

public class Parser {

    static Command parse(String fullCommand) throws DukeException {
            if (Parser.isBye(fullCommand)) {
                return new ByeCommand();
            } else if (Parser.isList(fullCommand)) {
                return new ListCommand();
            } else if (Parser.isMark(fullCommand)) {
                Parser.testMarkAndDelete(fullCommand);
                int taskIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
                return new MarkCommand(taskIndex);
            } else if (Parser.isUnmark(fullCommand)) {
                Parser.testMarkAndDelete(fullCommand);
                int taskIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
                return new UnmarkCommand(taskIndex);
            } else {
                if (Parser.isToDo(fullCommand)) {

                    String description = fullCommand.substring(4).trim();
                    // test whether the todo is valid
                    Parser.testToDo(description);

                    return new ToDoCommand(description);

                } else if (Parser.isEvent(fullCommand)) {

                    Parser.testEvent(fullCommand);
                    return new EventCommand(fullCommand);

                } else if (Parser.isDeadline(fullCommand)) {

                    Parser.testDeadline(fullCommand);
                    return new DeadlineCommand(fullCommand);

                } else if (Parser.isDelete(fullCommand)) {

                    Parser.testMarkAndDelete(fullCommand);
                    return new DeleteCommand(fullCommand);

                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }

    public static boolean isBye(String fullCommand) {
        return fullCommand.startsWith("bye");
    }

    public static boolean isList(String fullCommand) {
        return fullCommand.startsWith("list");
    }

    public static boolean isMark(String fullCommand) {
        return fullCommand.startsWith("mark");
    }

    public static boolean isUnmark(String fullCommand) {
        return fullCommand.startsWith("unmark");
    }

    public static boolean isToDo(String fullCommand) {
        return fullCommand.startsWith("todo");
    }

    public static boolean isEvent(String fullCommand) {
        return fullCommand.startsWith("event");
    }

    public static boolean isDeadline(String fullCommand) {
        return fullCommand.startsWith("deadline");
    }

    public static boolean isDelete(String fullCommand) {
        return fullCommand.startsWith("delete");
    }

    private static void testToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void testMarkAndDelete(String description) throws DukeException {
        String[] words = description.split(" ");
        String index = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeException("Following \"mark\" or \"unmark\" or \"delete\", an integer value is expected. Blanks or" +
                    " non-integer values are invalid.");
        }
    }

    private static void testEvent(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 3) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " start and end time.");
        }

        String title = list[0].substring(6);
        String start = list[1].substring(5);
        String end = list[2].substring(3);

        if (start.isEmpty()) {
            throw new DukeException("\"from\" time missing!");
        } else if (end.isEmpty()) {
            throw new DukeException("\"to\" time missing!");
        }
    }

    private static void testDeadline(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 2) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " end time.");
        }

        String time = list[1];

        if (!time.startsWith("by")) {
            throw new DukeException("Invalid input. Start with \"by\".");
        } else if (time.substring(2).equals(" ") || time.substring(2).isEmpty()) {
            throw new DukeException("Invalid input. Field Empty.");
        }
    }
}
