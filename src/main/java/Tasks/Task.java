package Tasks;

/**
 * A parent class that represents the task that the user will input.
 */
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

    /**
     * A method that will mark a task as marked.
     */
    public void markDone() {
        marked = true;
    }

    /**
     * A method that will unmark a task.
     */
    public void unmarkDone() {
        marked = false;
    }

    /*
    public boolean isMarked() {
        return marked;
    }
    public void hasError() { hasError = true; }
    public boolean isItErrored() { return hasError; }
    public void notTask() { isTask = false; }
    public boolean isItTask() { return isTask; }
    public void isValid() { isItValid = true; }
    public boolean isItValid() { return isItValid; }
     */

    /**
     * A method that will represent the task.
     *
     * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        String s = "  [" + type + "]" + "[" + (marked ? "X" : " ") + "] " + name;
        return s;
    }
}
