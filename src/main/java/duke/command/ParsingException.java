package duke.command;

import duke.DukeException;

public class ParsingException extends DukeException {

    enum ExceptionType {
        IMPROPER_FORMAT,
        MISSING_BODY,
        MISSING_DETAIL,
        MISSING_INDEX,
        NOT_A_NUMBER,
        EXCESS_INPUT
    }

    ExceptionType exceptionType;

    public ParsingException(ExceptionType exceptionType) {
        super("There was an error in parsing your input.");
        this.exceptionType = exceptionType;
    }

    @Override
    public String getMessage() {
        switch (exceptionType) {
            case IMPROPER_FORMAT:
                return "Please format your description properly.";

            case MISSING_BODY:
                return "I can't read your mind. Do add a description.";

            case MISSING_DETAIL:
                return "I can't read your mind. Do add more details.";

            case MISSING_INDEX:
                return "I can't read your mind. Provide a suitable index.";

            case NOT_A_NUMBER:
                return "I can't understand that number.";

            case EXCESS_INPUT:
                return "Sorry, you're speaking a little fast. Could you repeat just the first word?";

            default:
                return "Something went wrong.";
        }
    }

}
