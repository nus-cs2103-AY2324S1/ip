public class Deadline extends Task {
    private String endTime;

    public Deadline (String name, String endTime) {
        super(name);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endTime);
    }
}
