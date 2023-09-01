package sisyphus.task;

public class ToDo extends Task {
   public ToDo(String description) {
       super(description);
   }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " +  this.description;
    }

    @Override
    public String toSaveFormat() {
        return String.format("T,%s,%s", description, isDone ? "1" : "0");
    }
}
