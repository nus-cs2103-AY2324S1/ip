public class ToDo extends Task {

    public ToDo(String todoDesc) {
        super(todoDesc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
