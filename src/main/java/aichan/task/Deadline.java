package aichan.task;

public class Deadline extends Task{
    private String dueDate;
    public Deadline(String[] strs) {
        // first is taskName, second element is dueDate
        super(strs[0]);
        this.dueDate = strs[1];
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }
}
