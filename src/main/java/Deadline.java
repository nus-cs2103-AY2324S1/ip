public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String getTaskDesc() {
        if (!this.getDone()) {
            return "D |" + " 0 | " + this.getName() + "| " + this.date;
        } else
            return this.getName() + "| " + this.date;
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            System.out.println("TaskDesc: " + this.getTaskDesc());
            return "[D][ ] " + this.getName() + "(by: " + this.date + ")";
        } else {
            return "[D][X] " + this.getName() + "(by: " + this.date + ")";
        }
    }
}

