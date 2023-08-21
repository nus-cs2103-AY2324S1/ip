package duke;

public class Deadline extends Task{

    protected Tag tag = Tag.D;

    protected String deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;

    }
}
