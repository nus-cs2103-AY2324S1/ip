public class Deadline extends Task {
    private String dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
            return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Parse from string to a Deadline task
     *
     * @param line The String that is needed to parse into a Deadline Task
      */
    public static Deadline parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 4).split(" \\(by: ")[0];
        String dueDate = line.split("\\(by: |\\)")[1];
        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        Deadline task = new Deadline(description, dueDate);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }
        return task;
    }
}
