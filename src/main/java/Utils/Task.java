package Utils;

public class Task {
    private String title;

    protected Task(String title) {
        this.title = title;
    }

    protected String name() {
        return this.title;
    }
}
