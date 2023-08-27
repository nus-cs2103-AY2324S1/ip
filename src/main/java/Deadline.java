public class Deadline extends Task {
    String endTime;
    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.name +
                    " (by: " + this.endTime + ")";
        }

        return "[D][ ] " + this.name +
                " (by: " + this.endTime + ")";
    }
}
