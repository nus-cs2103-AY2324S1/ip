public class InvalidTaskFormatException extends Exception {

    public InvalidTaskFormatException(String field, String taskType) {
        super(String.format("OOPS!!! The %s of a %s cannot be empty.", field, taskType));
    }
}
