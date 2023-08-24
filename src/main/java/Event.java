import java.util.*;

public class Event extends Task {

    Event(boolean done, String name) {
        super(done, name);
    }

    Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
