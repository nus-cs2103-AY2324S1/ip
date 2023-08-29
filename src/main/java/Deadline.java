public class Deadline extends Task {

    String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    String getTaskType() {
        return "Deadline";
    }

    String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D][" + (this.isDone() ? 'X' : ' ') + "] " + this.name + " (by: " + this.getBy() + ")";
    }


}
