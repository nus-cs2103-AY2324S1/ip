public class Deadline extends Task{
    String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String display() {
        if(done) {
            return "[D][X] " + this.name + " (Due By: " + by + ")";
        }
        return "[D][] " + this.name + " (Due By: " + by + ")";
    }
}
