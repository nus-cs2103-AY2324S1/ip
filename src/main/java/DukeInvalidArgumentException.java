public class DukeInvalidArgumentException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "You provided invalid arguments :-(\n";
    }
}
