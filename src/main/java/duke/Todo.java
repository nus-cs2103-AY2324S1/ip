package duke;
public class Todo extends Task {
    //todo tasks have the same attributes as its parent Task
    public Todo(String name, boolean isMarked) {
        super(name, isMarked);
    }



    @Override
    public String toString() {
        if (isMarked) {
            return "[T][X] " + name;
        } else {
            return "[T][ ] " + name;
        }
    }
}

