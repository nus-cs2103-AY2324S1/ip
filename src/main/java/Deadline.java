public class Deadline extends Task{
    String time;
    public Deadline(String taskName, String time) {
        super(taskName);
        this.time = time;
    }
    @Override
    public String toMemoryFormat() {
        return String.format("T | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.time);
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (super.isDone ? "X" : " "), super.taskName, this.time);
    }
}
