public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String convertTaskToString() {
        return "T | " + (super.isDone() ? "1" : "0") + " | " + super.getName();
    }
}
