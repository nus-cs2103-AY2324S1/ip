package duke.task;


import duke.exception.DukeNoDescriptionException;

public class ToDo extends Task{
    public ToDo(String Description) throws DukeNoDescriptionException{
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("todo");
        }
        this.Description = Description.substring(5);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    public ToDo(String Description, boolean isDone) {
        super(Description);
        this.isDone = isDone;
    }
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
