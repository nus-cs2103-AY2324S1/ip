package potato;

public class PotatoException extends Throwable {
    protected String message;
    public PotatoException(String message) {
        System.out.println(message);
    }
}
