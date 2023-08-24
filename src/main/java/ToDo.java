public class ToDo extends Task{
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
