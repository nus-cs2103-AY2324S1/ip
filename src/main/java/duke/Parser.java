package duke;

import duke.command.*;
import duke.task.UpdateType;

/**
 * Represents a parser to process commands entered into the chatbot and returns the corresponding Command.
 */
public class Parser {

    /**
     * Parses the message to be processed into a MarkCommand, then returns that MarkCommand.
     *
     * @param message The full message to be parsed.
     * @return The MarkCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the MarkCommand.
     */
    private static MarkCommand parseMarkCommand(String message) throws DukeException {
        assert message.startsWith("mark") : message;
        if (message.length() <= 5) {
            throw new DukeException("You need to specify the index of the task to mark.");
        }
        try {
            return new MarkCommand(Integer.parseInt(message.substring(5)));
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to mark is not a valid integer.");
        }
    }

    /**
     * Parses the message to be processed into an UnmarkCommand, then returns that UnmarkCommand.
     *
     * @param message The full message to be parsed.
     * @return The UnmarkCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the UnmarkCommand.
     */
    private static UnmarkCommand parseUnmarkCommand(String message) throws DukeException {
        assert message.startsWith("unmark") : message;
        if (message.length() <= 7) {
            throw new DukeException("You need to specify the index of the task to unmark.");
        }
        try {
            return new UnmarkCommand(Integer.parseInt(message.substring(7)));
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to unmark is not a valid integer.");
        }
    }

    /**
     * Parses the message to be processed into a FindCommand, then returns that FindCommand.
     *
     * @param message The full message to be parsed.
     * @return The FindCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the FindCommand.
     */
    private static FindCommand parseFindCommand(String message) throws DukeException {
        assert message.startsWith("find") : message;
        if (message.length() <= 5) {
            throw new DukeException("You need to specify the keyword to find the tasks.");
        }
        return new FindCommand(message.substring(5));
    }

    /**
     * Parses the message to be processed into a DeleteCommand, then returns that DeleteCommand.
     *
     * @param message The full message to be parsed.
     * @return The DeleteCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the DeleteCommand.
     */
    private static DeleteCommand parseDeleteCommand(String message) throws DukeException {
        assert message.startsWith("delete") : message;
        if (message.length() <= 7) {
            throw new DukeException("You need to specify the index of the task to delete.");
        }
        try {
            return new DeleteCommand(Integer.parseInt(message.substring(7)));
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to delete is not a valid integer.");
        }
    }

    /**
     * Parses the message to be processed into an AddCommand that adds a todo, then returns that AddCommand.
     *
     * @param message The full message to be parsed.
     * @return The AddCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the AddCommand.
     */
    private static AddCommand parseTodoCommand(String message) throws DukeException {
        assert message.startsWith("todo") : message;
        if (message.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(message.substring(5));
    }

    /**
     * Parses the message to be processed into an AddCommand that adds a deadline, then returns that AddCommand.
     *
     * @param message The full message to be parsed.
     * @return The AddCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the AddCommand.
     */
    private static AddCommand parseDeadlineCommand(String message) throws DukeException {
        assert message.startsWith("deadline") : message;
        if (message.length() <= 9) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] deadline = message.substring(9).split(" /by ");
        if (deadline.length != 2) {
            throw new DukeException("A deadline requires exactly 1 due date.");
        }
        return new AddCommand(deadline[0], deadline[1]);
    }

    /**
     * Parses the message to be processed into an UpdateCommand that updates one parameter/feature of the task.
     *
     * @param message The full message to be parsed.
     * @return The UpdateCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the UpdateCommand.
     */
    private static UpdateCommand parseUpdateCommand(String message) throws DukeException {
        assert message.startsWith("update") : message;
        if (message.length() <= 7) {
            throw new DukeException("You need to specify which task and what to update.");
        }

        String[] updateDetails = message.substring(7).split(" ");

        if (updateDetails.length < 3) {
            throw new DukeException("You need to specify which task and what to update.");
        }

        int updateIndex;

        try {
            updateIndex = Integer.parseInt(updateDetails[0]);
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to update is not a valid integer.");
        }

        UpdateType updateType;
        String updateTypeStr = updateDetails[1];
        StringBuilder updateContent = new StringBuilder();

        for (int i = 2; i < updateDetails.length; i++) {
            updateContent.append(updateDetails[i]);
            if (i < updateDetails.length - 1) {
                updateContent.append(" ");
            }
        }

        switch (updateTypeStr.toLowerCase()) {
        case "message":
            // fallthrough
        case "msg":
            // fallthrough
        case "description":
            updateType = UpdateType.DESCRIPTION;
            break;
        case "date1":
            // fallthrough
        case "from":
            // fallthrough
        case "/from":
            // fallthrough
        case "deadline":
            // fallthrough
        case "/deadline":
            updateType = UpdateType.DATE1;
            break;
        case "date2":
            // fallthrough
        case "to":
            // fallthrough
        case "/to":
            updateType = UpdateType.DATE2;
            break;
        default:
            throw new DukeException("Update type is invalid!");
            // no break needed as exception is thrown
        }

        return new UpdateCommand(updateIndex, updateType, updateContent.toString());
    }

    private static CloneCommand parseCloneCommand(String message) throws DukeException {
        assert message.startsWith("clone") : message;
        if (message.length() <= 6) {
            throw new DukeException("You need to specify the index of the task to clone.");
        }
        try {
            return new CloneCommand(Integer.parseInt(message.substring(6)));
        } catch (NumberFormatException e) {
            throw new DukeException("The index of the task to clone is not a valid integer.");
        }
    }

    /**
     * Parses the message to be processed into an AddCommand that adds an event, then returns that AddCommand.
     *
     * @param message The full message to be parsed.
     * @return The AddCommand parsed from the given message if there are no errors.
     * @throws DukeException If there is an invalid input message causing an error in parsing the AddCommand.
     */
    private static AddCommand parseEventCommand(String message) throws DukeException {
        assert message.startsWith("event") : message;
        if (message.length() <= 6) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] event = message.substring(6).split(" /to | /from ");
        if (event.length != 3) {
            throw new DukeException("An event requires exactly 2 from/to dates.");
        }
        return new AddCommand(event[0], event[1], event[2]);
    }


    /**
     * Parses the message to be processed into a Command, then returns that Command.
     *
     * @param message The message to be parsed.
     * @return The Command parsed from the given message.
     * @throws DukeException If there is an invalid input message causing an error in parsing.
     */
    public static Command parse(String message) throws DukeException {
        String mainMessage = message.split(" ")[0];
        switch (mainMessage) {
        case "bye":
            return new ExitCommand();
            // return statement, no break needed
        case "list":
            return new ListCommand();
            // return statement, no break needed
        case "mark":
            return parseMarkCommand(message);
            // return statement, no break needed
        case "unmark":
            return parseUnmarkCommand(message);
            // return statement, no break needed
        case "find":
            return parseFindCommand(message);
            // return statement, no break needed
        case "delete":
            return parseDeleteCommand(message);
            // return statement, no break needed
        case "todo":
            return parseTodoCommand(message);
            // return statement, no break needed
        case "deadline":
            return parseDeadlineCommand(message);
            // return statement, no break needed
        case "update":
            return parseUpdateCommand(message);
            // return statement, no break needed
        case "event":
            return parseEventCommand(message);
            // return statement, no break needed
        case "clone":
            return parseCloneCommand(message);
            // return statement, no break needed
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
            // exception thrown, no break needed
        }
    }
}
