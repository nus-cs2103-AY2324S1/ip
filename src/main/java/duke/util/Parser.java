package duke.util;

import duke.exception.DukeException;

/**
 * Represents the parser that handles user commands.
 */
public class Parser {

    private String command;
    private String inputDetails;

    /**
     * Constructs the parser.
     * @param input User command.
     */
    public Parser(String input) {
        String[] parsedInput = this.parseCommand(input);
        this.command = parsedInput[0];
        if (parsedInput.length > 1) {
            this.inputDetails = parsedInput[1];
        }
    }

    /**
     * Parses the command into type of command and input details.
     * @param command User's command.
     * @return Array of type of command and input details.
     */
    public String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    /**
     * Get user's command.
     * @return User's command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Get input details.
     * @return Input details.
     */
    public String getInputDetails() {
        return this.inputDetails;
    }

    /**
     * Parses deadline task details.
     * @return Deadline task details.
     * @throws DukeException Missing description or deadline.
     */
    public String[] parseInputDetailsDeadline() throws DukeException {
        String[] deadlineInfo = inputDetails.split(" /by ");
        if (deadlineInfo.length < 2) {
            throw new DukeException(
                    "☹ OOPS!!! Missing description or deadline of deadline task. "
                            + "Valid Input Syntax: deadline desc /by date"
            );
        }
        return deadlineInfo;
    }

    /**
     * Parses event task details.
     * @return Event task details.
     * @throws DukeException Missing description or start or end date.
     */
    public String[] parseInputDetailsEvent() throws DukeException {
        String[] eventInfo = inputDetails.split(" /from | /to ");
        if (eventInfo.length < 3) {
            throw new DukeException(
                    "☹ OOPS!!! Missing description, start time or end time of deadline task. "
                            +
                            "Valid Input Syntax: event desc /from date /to date"
            );
        }
        return eventInfo;
    }

    /**
     * Checks if input details are present.
     * @return True if input details are present.
     */
    public boolean checkInputDetailsPresent() {
        return inputDetails != null;
    }
}
