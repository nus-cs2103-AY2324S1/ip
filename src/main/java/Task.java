public class Task {

    private final String details;

    public Task(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return this.details;
    }
}
