package src.alpha;
// Class representation of a ToDo
public class ToDo extends Task{

    public static ToDo makeToDo(String description) {
        return new ToDo(description);
    }
    private ToDo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}