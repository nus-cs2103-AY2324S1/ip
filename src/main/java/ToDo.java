public class ToDo extends Task{
    protected String taskDescription;
    private String identifier;

    public ToDo(String taskDescription) {
        super(taskDescription);
        this.identifier = "[T]";
    }

    public String toString() {
        return this.identifier + super.toString();
    }
}
