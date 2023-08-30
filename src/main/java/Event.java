public class Event extends Task{
    String startTime;
    String endTime;

    public Event(String name, String startTime, String endTime) {
        this.task_name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.startTime + this.endTime + ")";
    }
}
