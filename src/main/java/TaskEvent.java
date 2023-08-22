public class TaskEvent extends Task{
    String startTime, endTime;
    TaskEvent(Parser input) {
        super(input);
        super.oneLetterAbbrev = "E";
        startTime = input.getTaggedInput("from");
        endTime = input.getTaggedInput("to");
    }
    @Override
    public String toString() {
        return super.toString() + 
        " (from: " + this.startTime + " to: " + this.endTime + ")";
    }    
}
