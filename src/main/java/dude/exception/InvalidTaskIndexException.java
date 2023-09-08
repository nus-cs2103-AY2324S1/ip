package dude.exception;

public class InvalidTaskIndexException extends DudeException {
    public InvalidTaskIndexException(String index) {
        super(
                String.format("I can't find the task numbered \"%s\".\nTry checking if you've typed the correct task number.",
                        index)
        );
    }
}
