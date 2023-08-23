public class DukeException extends Exception {

    DukeException(String message) {
        super(message);
    }

    public String printError() {
        return getMessage();
    }


}
