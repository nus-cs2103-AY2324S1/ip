package duke.task;


import duke.exception.DukeNoDescriptionException;

/**
 * A todo class contains the description of the task.
 */
public class ToDo extends Task{
    /**
     * A construct, mainly used when reading inputs from user.
     * @param Description contains description of todo
     * @throws DukeNoDescriptionException if the description is empty
     */
    public ToDo(String Description) throws DukeNoDescriptionException{
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("todo");
        }
        Description = Description.replaceAll("\\s+", " ");
        this.Description = Description.substring(5);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    /**
     * A constructor, mainly used when reading from txt file.
     * @param Description description of the todo
     * @param isDone status of the todo
     */
    public ToDo(String Description, boolean isDone) {
        super(Description);
        this.isDone = isDone;
    }

    /**
     * Return a string containing the status and description.
     * @return a string containing info of todo
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
