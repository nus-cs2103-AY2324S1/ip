public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + this.description;
    }

    @Override
    public String fileString() {
        return "T|" + (this.isDone? 1: 0) + "|" + this.description;
    }
}
