public class Deadline extends Task {
    private final String end;

    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    @Override
    public String getTask() {
        return String.format("[%s][D] %s (by: %s)", super.checkDone(), super.getName(), end);
    }
}
