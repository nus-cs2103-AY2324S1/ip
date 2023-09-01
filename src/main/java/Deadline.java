public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString () {
        return "D" + super.toString() + " | " + this.by;
    }

    @Override
    public String toFileString() {
        char taskType = 'D';
        return taskType + " | " + super.toFileString() + " | " + by;
    }

    public static Deadline createDeadlineFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3 && taskParts[0].trim().equals("D")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();
            String by = taskParts[3].trim();

            Deadline deadline = new Deadline(description, by);
            if (doneStatus.equals("1")) {
                deadline.markDone();
            }
            return deadline;
        }

        return null; // incomplete data.txt
    }
}
