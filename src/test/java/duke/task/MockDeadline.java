package duke.task;

/**
 * Represents a mock deadline task in the Duke chatbot application.
 */
public class MockDeadline extends Deadline {
    public MockDeadline(String description) {
        super(description, null);
    }

    @Override
    public String toFileString() {
        return "Mock Deadline File String";
    }
}
