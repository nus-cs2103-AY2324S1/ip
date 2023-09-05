package peko;

public class InvalidTaskException extends Exception {
    private String errorMessage;

    public InvalidTaskException() {}

    public InvalidTaskException(String s) {
        this.errorMessage = s;
    }
    @Override
    public String toString() {
        return "That's Not a task BAKATARE!!!!";
    }

    public String errorToString() {
        return "Something is wrong with this task peko.Peko!\n" + errorMessage;
    }
}
