package sisyphus.task;

public class ToDo extends Task {
    /**
     * Constructor when provided description.
     *
     * @param description
     */
   public ToDo(String description) {
       super(description);
   }

    /**
     * Constructor when provided description and isDone state.
     *
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

     /**
     * Returns string representation.
     *
     * @return string representation with status icon and description.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " +  this.description;
    }

    /**
     * Returns string representation used for saveFormat which is csv.
     *
     * @return string representation with comma as separator.
     */
    @Override
    public String toSaveFormat() {
        return String.format("T,%s,%s", description, isDone ? "1" : "0");
    }
}
