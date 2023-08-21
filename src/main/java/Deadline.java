public class Deadline extends Task{

    protected String limit;

    public Deadline(String title, String limit) {
        super(title);
        this.limit = limit;
    }

    @Override
    public String toString() {
        String time = String.format(" (by: %s)", limit);
        return "[D]" + super.toString() + time;
    }
}
