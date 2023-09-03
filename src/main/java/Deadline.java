public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        int secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String by = taskData.substring(secondSplitIndex + 2);
        Deadline newDeadline =  new Deadline(desc, by);
        newDeadline.isDone = isDone;
        return newDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}