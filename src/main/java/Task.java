public class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
        System.out.println("____________________________________________________________\n" +
                "added: " + description + "\n" +
                "____________________________________________________________");
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
