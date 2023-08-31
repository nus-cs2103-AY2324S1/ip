package Duke.message;

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
