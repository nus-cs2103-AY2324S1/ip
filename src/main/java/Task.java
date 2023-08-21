public class Task {
    protected boolean marked;
    protected String description;
    public Task(String description) {
        this.description = description;
        this.marked = false;
    }
    public String getStatusIcon() {
        return marked ? "X" : " ";
    }

}
