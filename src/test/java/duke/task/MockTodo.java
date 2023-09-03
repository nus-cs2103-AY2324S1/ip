package duke.task;

/**
 * Represents a mock todo task in the Duke chatbot application.
 */
public class MockTodo extends Todo {
    public MockTodo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "Mock Todo File String";
    }
}
