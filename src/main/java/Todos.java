public class Todos extends Task {
    public Todos(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String printTask() {
        return this.getTypeIcon() + this.getStatusIcon() + this.description;
    }
}
