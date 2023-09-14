package Duke.Tasks;

import Duke.Parser;

public class EventTask extends Task {
    private String taskDescription;
    private String eventStartTime;
    private String eventEndTime;

    private boolean allowDuplicates = false;

    public EventTask(String task) {
        super(task);
        String[] inputStringComponents =  task.split("/");
        taskDescription = inputStringComponents[0];
        eventStartTime = inputStringComponents[1].split(" ", 2)[1];
        eventEndTime = inputStringComponents[2].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[E] | %s | %s | %s - %s",
                this.isDone() ? "[X]" : "[ ]",
                taskDescription,
                Parser.convertTimeToString(eventStartTime.trim()),
                Parser.convertTimeToString(eventEndTime.trim()));
    }

    @Override
    public boolean equals(Object o) {
        if ((!(o instanceof EventTask)) || allowDuplicates) {
            return false;
        }
        EventTask obj = (EventTask) o;
        if (obj.taskDescription.equals(this.taskDescription)
                && obj.eventEndTime.equals(this.eventEndTime)
                && obj.eventStartTime.equals(this.eventStartTime)) {
            allowDuplicates = true;
            return true;
        }
        return false;
    }
}
