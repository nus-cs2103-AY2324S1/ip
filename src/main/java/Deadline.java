public class Deadline extends Task {
    private String type;
    Deadline(String description) {
        super(description);
        this.type = "D";
    }

    public String getType() {
        return this.type;
    }
}
