public class ToDo extends Task {

    public ToDo(String task) {

        super(task);
    }

    @Override
    public String status() {
        return isDone() ? "[T][X]" : "[T][ ]";
    }

    @Override
    public String type() {
        return "ToDo";
    }
}
