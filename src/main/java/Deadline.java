import java.util.*;

public class Deadline extends Task {

    Deadline(boolean done, String name) {
        super(done, name);
    }

    Deadline(String name) {
        super(name);
    }

    @Override
    public String taskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }
}
