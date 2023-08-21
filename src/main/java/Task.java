public class Task {
    private final String description;

    Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
