public class Deadline extends Task {

    private String time;
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String storeFormat() {
        String taskType = "D";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        return (taskType + " | " + isTaskDone + " | " + this.description + " | " + this.time);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
