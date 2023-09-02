package exceptions;

public class IllegalFormatException extends Exception {
    private String msg;
    public IllegalFormatException(String s) {
        msg = s;
    }

    public String toString() {
        return msg;
    }
}
