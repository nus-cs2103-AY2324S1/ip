package adam.tasks;

import java.io.Serializable;

public class ToDos extends Task implements Serializable {
    public ToDos(String text){
        super(text);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
