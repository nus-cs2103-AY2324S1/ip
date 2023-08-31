package adam.exception;

public class OutOfBoundException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! The number you put in is more than the current item in your list";
    }
}
