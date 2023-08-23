public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    public String type() {
        return "T";
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s", type, super.toString() );
    }
}
