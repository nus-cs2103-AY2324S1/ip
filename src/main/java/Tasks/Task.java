package Tasks;

public class Task {
    private String name;
    private boolean marked;
    private boolean hasError;
    private boolean isTask;
    private boolean isItValid;
    public String type;
    public Task(String name) {
        this.name = name;
        this.marked = false;
        this.hasError = false;
        this.isTask = true;
        this.isItValid = false;
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
    public void hasError() { hasError = true; }
    public boolean isItErrored() { return hasError; }
    public void notTask() { isTask = false; }
    public boolean isItTask() { return isTask; }
    public void isValid() { isItValid = true; }
    public boolean isItValid() { return isItValid; }
    @Override
    public String toString() {
        String s = "  [" + type + "]" + "[" + (marked ? "X" : " ") + "] " + name;
        return s;
    }
}
