package dude;
public class DudeException extends Exception {
    DudeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
