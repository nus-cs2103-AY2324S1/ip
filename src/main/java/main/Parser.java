package main;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnmarkCommand;
import command.UpdateDescriptionCommand;
import exception.DukeException;

import java.util.Arrays;

/**
 * Parser class to read user input
 */
public class Parser {

    /**
     * parse method reads users input and directs the course of action.
     *
     * @param fullCommand user input
     * @return return an Object belonging to the command class for further execution.
     * @throws DukeException organic exception to Duke - subclass of Exception class
     */
    static Command parse(String fullCommand) throws DukeException {
        if (Parser.isBye(fullCommand)) {
            assert fullCommand.startsWith("bye") : "Should start with Bye";
            return new ByeCommand();
        } else if (Parser.isList(fullCommand)) {
            assert fullCommand.startsWith("list") : "Should start with list";
            return new ListCommand();
        } else if (Parser.isMark(fullCommand)) {
            assert fullCommand.startsWith("mark") : "Should start with mark";
            int taskIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
            return new MarkCommand(taskIndex);
        } else if (Parser.isUnmark(fullCommand)) {
            assert fullCommand.startsWith("unmark") : "Should start with unmark";
            int taskIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            return new UnmarkCommand(taskIndex);
        } else if (Parser.isToDo(fullCommand)) {
            assert fullCommand.startsWith("todo") : "Should start with todo";
            String description = fullCommand.substring(4).trim();
            return new ToDoCommand(description);

        } else if (Parser.isEvent(fullCommand)) {
            assert fullCommand.startsWith("event") : "Should start with event";
            return new EventCommand(fullCommand);

        } else if (Parser.isDeadline(fullCommand)) {
            assert fullCommand.startsWith("deadline") : "Should start with deadline";
            return new DeadlineCommand(fullCommand);

        } else if (Parser.isDelete(fullCommand)) {
            assert fullCommand.startsWith("delete") : "Should start with delete";
            return new DeleteCommand(fullCommand);

        } else if (Parser.isFind(fullCommand)) {
            assert fullCommand.startsWith("find") : "Should start with find";
            return new FindCommand(fullCommand);
        } else if (Parser.isUpdateDescription(fullCommand)) {
            return new UpdateDescriptionCommand(fullCommand);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isBye(String fullCommand) {
        return fullCommand.equals("bye");
    }

    private static boolean isList(String fullCommand) {
        return fullCommand.startsWith("list");
    }

    private static boolean isMark(String fullCommand) throws DukeException {
        boolean isMarkCommand = fullCommand.startsWith("mark");
        if (isMarkCommand) {
            testMarkAndDelete(fullCommand);
        }

        return isMarkCommand;
    }

    private static boolean isUnmark(String fullCommand) throws DukeException {
        boolean isUnmarkCommand = fullCommand.startsWith("unmark");

        if (isUnmarkCommand) {
            testMarkAndDelete(fullCommand);
        }

        return isUnmarkCommand;
    }

    private static boolean isToDo(String fullCommand) throws DukeException {
        boolean isToDoCommand = fullCommand.startsWith("todo");
        String description = fullCommand.substring(4).trim();

        if (isToDoCommand) {
            testToDo(description);
        }

        return isToDoCommand;
    }

    private static boolean isEvent(String fullCommand) throws DukeException {
        boolean isEventCommand = fullCommand.startsWith("event");

        if (isEventCommand) {
            testEvent(fullCommand);
        }

        return isEventCommand;
    }

    private static boolean isDeadline(String fullCommand) throws DukeException {
        boolean isDeadlineCommand = fullCommand.startsWith("deadline");

        if (isDeadlineCommand) {
            testDeadline(fullCommand);
        }

        return isDeadlineCommand;
    }

    private static boolean isDelete(String fullCommand) throws DukeException {
        boolean isDeleteCommand = fullCommand.startsWith("delete");

        if (isDeleteCommand) {
            testMarkAndDelete(fullCommand);
        }

        return isDeleteCommand;
    }

    public static boolean isFind(String fullCommand) {
        String[] inputArray = fullCommand.split(" ");
        return inputArray[0].equals("find");
    }

    public static boolean isUpdateDescription(String fullCommand) throws DukeException {
        boolean isUpdateDescriptionCommand = fullCommand.startsWith("update description");

        if (isUpdateDescriptionCommand) {
            String information = fullCommand.substring(18).trim();
            testUpdateDescription(information);
        }

        return isUpdateDescriptionCommand;
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

    private static void testUpdateDescription(String description) throws DukeException {
        String[] list = description.split(" ");

        try {
            // this is just to test whether there is an integer following the update description command
            Integer.parseInt(list[0].trim());
        } catch (NumberFormatException ex) {
            throw new DukeException("update description should be followed by an integer, which represents" +
                    " the index of the task description to be updated.");
        }

        if (list[2].isEmpty()) {
            throw new DukeException("missing description field");
        }

        // by this stage, it has been confirmed that there is an integer value after update description and there is
        // a description field.
        String[] slashList = description.split("/");

        String update = slashList[1]; // the information on the update should be the second element in the list

        if (!update.startsWith("update")) {
            throw new DukeException("Invalid input. Start with \"update\".");
        } else if (update.substring(6).trim().isEmpty()) {
            throw new DukeException("Empty input");
        }

    }
}
