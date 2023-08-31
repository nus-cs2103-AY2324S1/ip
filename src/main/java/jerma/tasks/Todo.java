package jerma.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.symbol = "T";
    }

    @Override
    public String save() {
        return String.format("%s|%s", this.symbol, super.save());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.symbol, super.toString());
    }
}
