public class DukeNoTaskFoundException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "No such task found :-(\n";
    }
}
