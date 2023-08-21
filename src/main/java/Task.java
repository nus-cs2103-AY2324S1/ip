public class Task {
    private final String name;
    private boolean marked = false;

    public Task(String name) {
        this.name = name;
    }

    public void markTask() {
        this.marked = true;
    }

    public void unMarkTask() {
        this.marked = false;
    }

    @Override
    public String toString() {
        String checkBox;
        if (this.marked) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s %s", checkBox, this.name);
    }
}