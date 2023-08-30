package exceptions;

public class InvalidInputException extends DukeException {
    public InvalidInputException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
    }
}
