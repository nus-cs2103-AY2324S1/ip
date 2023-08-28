public class InvalidTaskException extends Exception{
    private boolean timeProblem = false;
    private String defaultMessage = "That's Not a task BAKATARE!!!! \n";

    @Override
    public String toString() {
        return timeProblem ? this.errorMessage : defaultMessage;
    }

    public String errorToString() {
        return "Something is wrong with this task Peko!\n" + errorMessage;
    }
    String errorMessage;

    public InvalidTaskException(String s, boolean timeProblem) {
        this.timeProblem = timeProblem;
        this.errorMessage = s;
    }
    public InvalidTaskException(boolean timeProblem){
        this.timeProblem = timeProblem;
    }
    public InvalidTaskException(){}
}
