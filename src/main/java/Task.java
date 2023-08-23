public class Task {
    private String name;
    private boolean status;
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }
    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }
}
