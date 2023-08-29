package task;

public abstract class Task {
    protected String name;
    protected boolean status;
    protected TaskTypes type;

    protected static String DISCRIMINATOR = " || ";

    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }

    public abstract String toSave();
}
