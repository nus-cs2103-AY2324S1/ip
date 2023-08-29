package duke.task;

public class MockEvent extends Event {
    public MockEvent(String description) {
        super(description, null);
    }

    @Override
    public String toFileString() {
        return "Mock Event File String";
    }
}
