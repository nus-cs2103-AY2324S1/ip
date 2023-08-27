public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description, false);
    }

    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }
    public String toSave() {
        return "[T]" + super.toString();
    }



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
