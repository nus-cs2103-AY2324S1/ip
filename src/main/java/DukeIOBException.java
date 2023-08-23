public class DukeIOBException extends IndexOutOfBoundsException{
    public DukeIOBException(int idx) {
        super("Task " + idx + " does not exist");
    }
}
