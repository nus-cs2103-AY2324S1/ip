public class InvalidTaskIndexException extends Exception {

    public InvalidTaskIndexException(int index, int size) {
        super(String.format("Could not update task %d! There are %d tasks available.", index, size));
    }
}
