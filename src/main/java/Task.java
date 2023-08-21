abstract class Task {
    protected boolean marked;
    protected String description;
    public Task(String description) {
        this.description = description;
        this.marked = false;
    }
    public String getStatusIcon() {
        return marked ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.marked = true;
    }

    public void markAsUnDone() {
        this.marked = false;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

}
