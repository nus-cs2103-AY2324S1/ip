public class Event extends Task {
    private String startDate;
    private String dueDate;
    public Event(String taskName, String startDate,String dueDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + dueDate + ")";
    }

    /**
     * Parse from string to a Event task
     *
     * @param line The String that is needed to parse into a Event Task
     */
    public static Event parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 4).split(" \\(from: | to: ")[0];
        String startDate = line.split(" \\(from: | to: |\\) ")[1];
        String dueDate = line.split(" \\(from: | to: |\\)")[2];
        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        Event task = new Event(description, startDate, dueDate);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }
        return task;
    }
}
