public class Task {
    private String title;
    private boolean isMarked = false;

    public Task(String title) {
        this.title = title;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString(){
        String mark = this.isMarked ? "[X] " : "[ ] ";
        return mark + title;
    }
}
