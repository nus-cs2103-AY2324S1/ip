public class Deadline extends Task{
    protected String deadline ;

    public Deadline(String name, String deadline){
        super(name);
        this.deadline = deadline;
    }

    // Override toString method
    @Override public String toString(){
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }

}
