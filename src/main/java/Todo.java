public class Todo extends Task {


    public Todo(String description) {

        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
