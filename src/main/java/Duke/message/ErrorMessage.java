package Duke.message;

/**
 * Represents an error message that can be displayed to the user.
 * Extends the {@link Message} class.
 */
public class ErrorMessage extends Message{

    String errorLine = "!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!";
    public ErrorMessage(String content) {
        super(content);
    }

    @Override
    public void print() {
        System.out.println(errorLine);
        System.out.println(this);
        System.out.println(errorLine);
    }

}
