package duke.task;

/**
 * Represents a mock event task in the Duke chatbot application.
 */
public class MockEvent extends Event {
    public MockEvent(String description) {
        super(description, null);
    }

    @Override
    public String toFileString() {
        return "Mock Event File String";
    }
}
