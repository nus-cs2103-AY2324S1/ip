public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {

        String result = "[T]" + super.toString();
        return result;
    }
}
