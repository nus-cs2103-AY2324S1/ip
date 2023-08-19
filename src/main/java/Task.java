public class Task {
    private boolean isDone;
    private String content;



    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    private String get_status_icon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public  String toString() {
        return "[" + get_status_icon() + "] " + this.content;
    }
}
