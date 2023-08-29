package peko;

public class InvalidTaskException extends Exception{

    @Override
    public String toString() {
        return "That's Not a task BAKATARE!!!!";
    }

    public String errorToString() {
        return "Something is wrong with this task peko.Peko!\n" + errorMessage;
    }
    String errorMessage;

    public InvalidTaskException(String s) {
        this.errorMessage = s;
    }
    public InvalidTaskException(){}
}
