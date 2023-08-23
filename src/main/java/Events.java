public class Events extends Task{
    private final String start;
    private final String end;
    public Events(String task, int taskId, String start, String end) {
        super(task, taskId);
        this.start = start;
        this.end = end;
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }

    @Override
    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String startTime = "(from: " + start.substring(5);
        String endTime = "to: " + end.substring(3) + ")";
        return taskId + "." + "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String startTime = "(from: " + start.substring(5);
        String endTime = "to: " + end.substring(3) + ")";
        return "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
}
