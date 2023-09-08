package components;

public class DukeException extends Exception {
  
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("😭 OOPS!!! %s", super.getMessage());
    }
}
