public class Task {
    private boolean completed;
    private String description, type;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    public void mark() {
        completed = true;
    }

    public void unmark() {
        completed = false;
    }

    @Override
    public String toString() {
        String mark = "[ ]";
        if (this.completed) {
            mark = "[X]";
        }
        return mark + " " + description;
    }
}
