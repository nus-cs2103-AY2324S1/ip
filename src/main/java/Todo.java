public class Todo extends Tasks {

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

