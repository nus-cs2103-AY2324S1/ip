public class Deadline extends Task {
    private String by;

    public Deadline(String task, Boolean isNotSaved, String by) {
        super(task, isNotSaved);
        this.by = by;
        if (isNotSaved) {
            saveToFile();
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }

    public String generateStr() {
        return "D | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + by;
    }

    @Override
    public void saveToFile() {
        Duke.saveTaskToFile(generateStr());
    }

}
