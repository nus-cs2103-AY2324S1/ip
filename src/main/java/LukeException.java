public class LukeException extends Exception {
    LukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
