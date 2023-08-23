public class BobException extends Exception{

    protected String message;

    public BobException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
