public class DukeInvalidTaskException extends DukeException {

    String part;
    String type;

    DukeInvalidTaskException(String part, String type) {
        this.part = part;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Error: %s of %s cannot be empty!", this.part, this.type);
    }
}
