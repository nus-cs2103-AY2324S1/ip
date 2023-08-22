public class Task {
    private boolean completed;
    private String description, type;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
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
