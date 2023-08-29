package pooh;

public class WriteTasksException extends PoohException {
    @Override
    public String toString() {
        return "      An error has occurred whilst writing to file. :-(";
    }
}
