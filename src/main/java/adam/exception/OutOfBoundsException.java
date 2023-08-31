package adam.exception;

public class OutOfBoundsException extends AdamException {
    public String getInfo() {
        return "OOPS!!! The number you put in is more than the current item in your list";
    }
}
