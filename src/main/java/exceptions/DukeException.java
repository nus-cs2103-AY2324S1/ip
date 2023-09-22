package exceptions;

public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "/ᐠ｡ⱉ｡ᐟ\\ﾉ OOPS! " + super.toString();
    }
}
