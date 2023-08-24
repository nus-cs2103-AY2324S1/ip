public class Task {
    private String description;
    private Boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void Mark() {
        isCompleted = true;
    }

    public void Unmark() {
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
