public class Deadline extends Task {
    protected String date;
    Deadline(String name, String date) {
        super(name);
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public String identifier() {
        return "D";
    }
    @Override
    public String toString() {
        return String.format("[%s] [%s] %s (by: %s)", this.identifier(), this.showStatus(), this.showName(), this.getDate());
    }
}
