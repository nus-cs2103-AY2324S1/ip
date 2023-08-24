import java.util.*;

public class ToDo extends Task {

    ToDo(boolean done, String name) {
        super(done, name);
    }

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
