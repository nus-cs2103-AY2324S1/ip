package duke.task;

public class MockTodo extends Todo {
    public MockTodo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "Mock Todo File String";
    }
}
