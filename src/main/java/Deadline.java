public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[D][ ] " + this.getName() + "(by: " + this.date + ")";
        } else {
            return "[D][X] " + this.getName() + "(by: " + this.date + ")";
        }
    }
}

