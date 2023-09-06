package tasks;

public class Task {
    private String content;
    private boolean marked;

    /**
     * Constructs a task with an initial status of not marked
     */
    public Task(String content) {
        this.content = content;
        this.marked = false;
    }

    /**
     * Constructs a task with an initial status that is marked
     */
    public Task(String content, boolean status) {
        this.content = content;
        this.marked = status;
    }

    /**
     * @return the task as marked
     */
    public Task mark() {
        return new Task(content, true);
    }

    /**
     * @return the task as unmarked
     */
    public Task unmark() {
        return new Task(content);
    }

    /**

     * @param listSize size of the TaskList
     * @return a String representation of the message displayed when adding a task to TaskList
     */
    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "added: " + this.content + "\n" +
                "____________________________________________________________";
    }

    /**
     * @return the content of the task as a string
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return the boolean value of whether the task is marked
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * To String method
     * @return a string describing the status of the task
     */
    public String toString() {
        if (!this.marked) {
            return String.format("[ ] %s", content);
        } else {
            return String.format("[X] %s", content);
        }
    }
}
