package Duke.message;

public class ErrorMessage extends Message{

    String errorLine = "!-!-!-!-!-!-!-!-!-!-!-!-!-!-!-!";
    public ErrorMessage(String content) {
        super(content);
    }

    @Override
    public void Print() {
        System.out.println(errorLine);
        System.out.println(this.ToString());
        System.out.println(errorLine);
    }

}
