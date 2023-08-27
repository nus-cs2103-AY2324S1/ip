package Exception;

public class MissingDateArgumentException extends MissingArgumentException {
    private String string;
    public MissingDateArgumentException(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "  ☹ OOPS!!! Please type the command: '/" + this.string + "' followed by the specified date in any format.";
    }
}
