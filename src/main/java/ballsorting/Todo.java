package ballsorting;
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "T|" + stat + "|" + this.description;
    }
}
