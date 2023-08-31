package adam.tasks;

import java.io.Serializable;
public class ToDo extends Task implements Serializable {
    public ToDo(String text) {
        super(text);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
