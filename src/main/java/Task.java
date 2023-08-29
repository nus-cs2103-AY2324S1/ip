public class Task {
    private String description;
    private Boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void mark() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        String marking;
        if (isCompleted) {
            marking = "[X]";
        } else {
            marking = "[ ]";
        }
        return marking + " " + description;
    }
}
