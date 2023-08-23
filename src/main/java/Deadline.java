public class Deadline extends Task{

    public Deadline(String deadline) {
        super(deadline);
    }

    @Override
    public String eventCode() {
        return "D";
    }

    @Override
    public String eventDescription() {
        String taskWithoutDeadline = super.eventDescription();
        int i = taskWithoutDeadline.indexOf('/');
        return String.format("%s (by: %s)", taskWithoutDeadline.substring(0, i-1), taskWithoutDeadline.substring(i+4));
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.eventCode(), this.getStatusIcon(), this.eventDescription());
    }

    @Override
    public String missingDescription() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}
