package main.java.exception;

public class InvalidTaskIndexException extends MilException {
    public InvalidTaskIndexException() {
        super("☹ Oopsie! The index you input does not match any task.");
    }
}
