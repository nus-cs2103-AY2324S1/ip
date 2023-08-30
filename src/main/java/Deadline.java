public class Deadline extends Task{
    protected final String endDate;
    public Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = endDate;
    }

    public String toString(){
        return " [D]" + (super.isDone ? "[X] " : "[ ] ") +  super.toString() + " (by: " + endDate + ")";
    }
}
