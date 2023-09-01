package Duke.DukeException;

public class DukeException extends Exception{
    public DukeException(String ErrMsg) {
        super(ErrMsg);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
