public class Deadline extends Task {
    private static String SYMBOL = "D";
    protected String dueDatetime;

    public Deadline(String description, String dueDatetime) {
        super(description);
        this.dueDatetime = dueDatetime;
    }

    @Override
    public String getDataString() {
        return String.join(" | ", Deadline.SYMBOL, super.isDone ? "1" : "0", super.getDescription(), this.dueDatetime);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.SYMBOL, super.toString(), this.dueDatetime);
    }
}
