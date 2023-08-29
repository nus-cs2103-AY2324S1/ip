public class Deadlines extends Task {
    private String datetime;
    public Deadlines(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    @Override
    public String getSymbol() {
        return "D";
    }

    @Override
    public String getDescription() {
        String description = String.format("%s (by: %s)", super.getName(), this.datetime);
        return description;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + datetime;
    }
}
