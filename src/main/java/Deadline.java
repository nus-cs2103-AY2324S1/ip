//Deadline class
public class Deadline extends Task{
    String by;
    //Stores the due date as by
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    //default display for this type
    public String display() {
        if(done) {
            return "[D][X] " + this.name + " (Due By: " + by + ")";
        }
        return "[D][] " + this.name + " (Due By: " + by + ")";
    }
}
