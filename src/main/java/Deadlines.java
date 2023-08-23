public class Deadlines extends Task {
    private String datetime;
    public Deadlines(String name, String datetime) {
        super(name);
        this.datetime = datetime;
    }

    public String getDatetime() {
        return this.datetime;
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
}
