public class InvalidTaskStringException extends Exception {
    String taskNum;

    @Override
    public String toString() {
        return "Error: Unable to read task file!";
    }
}
