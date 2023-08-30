public class Event extends Task{
    String from;
    String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
        saveToFile();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + "to:" + to + ")";
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }

    public void saveToFile() {
        String taskString =  "E | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + from + " | "+to;
        Duke.saveTaskToFile(taskString);
    }
}
