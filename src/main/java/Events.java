public class Events extends Task {
    private String from;
    private String to;
    public Events(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }



    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public String getDescription() {
        String description = String.format("%s (from: %s to: %s)", super.getName(), from, to);
        return description;
    }

    @Override
    public String toFileFormat() {
        return super.toFileFormat() + " | " + from + " | " + to;
    }
}
