package duke;

public class Deadline extends Task{

    protected String deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
        this.tag = Tag.D;

    }

    @Override
    public String toString(){
        return String.format("%s (by: %s)",description,deadline);
    }
}
