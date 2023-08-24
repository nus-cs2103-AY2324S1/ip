public class InvalidFormatException extends DukeException {

    public InvalidFormatException(String formatDescription) {
        super(" ☹ OOPS!!! The format of the command is invalid. \n\t " + formatDescription);
    }
}
