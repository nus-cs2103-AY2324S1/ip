public class Task {
    private String name;
    private Boolean marked;
    public String type;
    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public void markDone() {
        marked = true;
    }
    public void unmarkDone() {
        marked = false;
    }
    public boolean isMarked() {
        return marked;
    }
    @Override
    public String toString() {
        String s = "  [" + type + "]" + "[" + (marked ? "X" : " ") + "] " + name;
        return s;
    }
}
