package duke.task;

public abstract class Task {
    protected String name;
    protected boolean status;
    protected TaskTypes type;

    protected static String DISCRIMINATOR = " || ";

    public Task mark() {
        this.status = true;
        return this;
    }

    public Task unmark() {
        this.status = false;
        return this;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }

    public abstract String toSave();
}
