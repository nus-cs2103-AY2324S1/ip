package duke.util;

import duke.exception.DukeException;

public class Parser {

    private String command;
    private String inputDetails;

    public Parser(String input) {
        String[] parsedInput = this.parseCommand(input);
        this.command = parsedInput[0];
        if (parsedInput.length > 1) {
            this.inputDetails = parsedInput[1];
        }
    }
    public String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    public String getCommand() {
        return this.command;
    }

    public String getInputDetails() {
        return this.inputDetails;
    }

    public String[] parseInputDetailsDeadline() throws DukeException {
        String[] deadlineInfo = inputDetails.split(" /by ");
        if (deadlineInfo.length < 2) {
            throw new DukeException(
                    "☹ OOPS!!! Missing description or deadline of deadline task. " +
                            "Valid Input Syntax: deadline desc /by date"
            );
        }
        return deadlineInfo;
    }

    public String[] parseInputDetailsEvent() throws DukeException {
        String[] eventInfo = inputDetails.split(" /from | /to ");
        if (eventInfo.length < 3) {
            throw new DukeException(
                    "☹ OOPS!!! Missing description, start time or end time of deadline task. " +
                            "Valid Input Syntax: event desc /from date /to date"
            );
        }
        return eventInfo;
    }

    public boolean checkInputDetailsPresent() {
        return inputDetails != null;
    }
}
