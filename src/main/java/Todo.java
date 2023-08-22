public class Todo extends Task {

    public Todo(String title) {
        super(title);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}

