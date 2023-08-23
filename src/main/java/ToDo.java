public class ToDo extends Task {
    private String type;
    ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public String getType() {
        return this.type;
    }
}
