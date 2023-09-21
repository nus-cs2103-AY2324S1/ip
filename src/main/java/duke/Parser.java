package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;


/**
 * Provides utilities for parsing user inputs into commands for the Duke application.
 */
public class Parser {

    /**
     * Parses the given user input string into a Command object.
     * This method recognizes various command keywords and constructs the appropriate command object.
     *
     * @param userInput The raw input string from the user.
     * @return The appropriate Command object based on the user input.
     * @throws DukeException If the user input is not recognized or improperly formatted.
     */
    public static Command parse(String userInput) throws DukeException {
        assert userInput != null : "User input should not be null";

        if ("bye".equalsIgnoreCase(userInput)) {
            return new ByeCommand();
        } else if ("list".equalsIgnoreCase(userInput)) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ");
            assert parts.length > 1 : "Expected a task number after 'mark'";

            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                return new MarkCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number.");
            }
        } else if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ");
            assert parts.length > 1 : "Expected a task number after 'unmark'";

            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                return new UnmarkCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number.");
            }
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(4).trim();
            return new TodoCommand(description);
        } else if (userInput.startsWith("deadline")) {
            String content = userInput.substring(8).trim();
            int index = content.indexOf("/by");

            if (index == -1) {
                throw new DukeException("Please use '/by' to specify the deadline time.");
            } else {
                String description = content.substring(0, index).trim();
                String by = content.substring(index + 4).trim();
                return new DeadlineCommand(description, by);
            }
        } else if (userInput.startsWith("event")) {
            String content = userInput.substring(5).trim();
            String[] parts = content.split("/from | /to ");

            if (parts.length < 3) {
                throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
            }

            String description = parts[0].trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            return new EventCommand(description, from, to);
        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");
            assert parts.length > 1 : "Expected a task number after 'delete'";
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide a valid task number.");
            }
        } else if (userInput.startsWith("find")) {
            String keyword = userInput.substring(4).trim();
            return new FindCommand(keyword);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
