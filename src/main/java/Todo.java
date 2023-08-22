public class Todo extends Task{
    protected String type = "T";

    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}
