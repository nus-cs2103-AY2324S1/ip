package Utils;

public class Task {
    private static final String MARKED_CHECKBOX = "[X]";
    private static final String UNMARKED_CHECKBOX = "[ ]";
    private String title;
    private boolean checked;

    protected Task(String title) {
        this.title = title;
        this.checked = false;
    }

    protected String name() {
        return this.title;
    }

    protected void mark() {
        this.checked = true;
    }

    protected void unmark() {
        this.checked = false;
    }

    @Override
    public String toString() {
        return String.format("%s %s",
            this.checked ? Task.MARKED_CHECKBOX : Task.UNMARKED_CHECKBOX,
            this.name()
          );
    }
}
