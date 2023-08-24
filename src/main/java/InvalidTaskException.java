public class InvalidTaskException extends Exception {

    String part;
    String type;

    InvalidTaskException(String part, String type) {
        this.part = part;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Error: %s of %s cannot be empty!", this.part, this.type);
    }
}
