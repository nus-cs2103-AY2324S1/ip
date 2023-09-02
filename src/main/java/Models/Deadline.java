package Models;

public class Deadline extends Task {
    String endTime;
    public Deadline(String name, Boolean marked, String endTime) {
        super(name, marked);
        this.endTime = endTime;
    }

    @Override
    public String getTaskDetails() {
        return "Deadline," + this.name + "," + this.isMarked + "," + this.endTime;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[D][X] " + this.name +
                    " (by: " + this.endTime + ")";
        }

        return "[D][ ] " + this.name +
                " (by: " + this.endTime + ")";
    }
}
