package Eddie;

public class NoSuchCommandException extends DukeException{
    public NoSuchCommandException() {
        super("There is no such command!");
    }
}
