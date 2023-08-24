public class Todo extends Task{
    /**
     * Make new Todo
     * @param description todo description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
