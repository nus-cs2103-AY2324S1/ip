public class Deadline extends Task {
    private String finishByTime;
    Deadline(String name, String finishByTime) {
        super(name);
        this.finishByTime = finishByTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + finishByTime + ")";
    }
}
