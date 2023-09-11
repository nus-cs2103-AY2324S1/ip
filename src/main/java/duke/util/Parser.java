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
     * @param input User's command.
     * @return Array of type of command and input details.
     */
    public String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Get user's command.
     * @return User's command.
     */
    public String getCommand() {
        assert (command != null) : "Command cannot be null.";
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
        assert (inputDetails != null) : "Input details cannot be null.";
        String[] deadlineInfo = inputDetails.split(" /by ");
        boolean invalidDeadlineInfo = deadlineInfo.length < 2;
        if (invalidDeadlineInfo) {
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
        assert (inputDetails != null) : "Input details cannot be null.";
        String[] eventInfo = inputDetails.split(" /from | /to ");
        boolean invalidEventInfo = eventInfo.length < 3;
        if (invalidEventInfo) {
            throw new DukeException(
                    "☹ OOPS!!! Missing description, start time or end time of deadline task. "
                            +
                            "Valid Input Syntax: event desc /from date /to date"
            );
        }
        return eventInfo;
    }

    public String[] parseRescheduleInfo() throws DukeException {
        assert (inputDetails != null) : "Input details cannot be null.";
        String[] rescheduleInfo = inputDetails.split(" ", 2);
        boolean invalidRescheduleInfo = rescheduleInfo.length < 2;
        if (invalidRescheduleInfo) {
            throw new DukeException("☹ OOPS!!! Missing task, or new time details of rescheduling task.");
        }
        return rescheduleInfo;
    }

    /**
     * Checks if input details are present.
     * @return True if input details are present.
     */
    public boolean checkInputDetailsAbsent() {
        return inputDetails == null;
    }
}
