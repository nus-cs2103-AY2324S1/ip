public class Deadline extends Task {
    private String by;
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getType() {
        return "deadline";
    }
    @Override
    public String saveTask() {
        String data = "D | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += this.getDescription();
        data = data + " | " + this.by + "\n";
        return data;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
