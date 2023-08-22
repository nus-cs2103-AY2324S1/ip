public class Task extends Duke {
    private String description;
    private boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public void mark() {
        isMarked = true;
    }

    public void unMark() {
        isMarked = false;
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    public boolean getMarkedStatus() {
        return isMarked;
    }

    public String getDescription() {
        return getStatusIcon() + " " + description;
    }
}
