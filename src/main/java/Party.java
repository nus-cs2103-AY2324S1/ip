public class Party extends Task {
    protected String from;
    protected String to;

    public Party(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "{P}" + super.toString() + "~from: " + this.from + ", to: " + this.to + "~";
    }
}
