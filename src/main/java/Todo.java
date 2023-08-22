public class Todo extends Task{

    private static String TYPE = "[T]";

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return this.TYPE + super.toString();
    }
}
