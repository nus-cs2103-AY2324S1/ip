public class TaskEvent extends Task{
    String startTime, endTime;
    TaskEvent(String taskName, String startTime, String endTime) {
        super(taskName);
        super.oneLetterAbbrev = "E";
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return super.toString() + 
        " (from: " + this.startTime + " to: " + this.endTime + ")";
    }    
}
