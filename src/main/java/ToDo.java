public class ToDo extends Task{

    public ToDo(String Description) throws DukeNoDescriptionException{
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("todo");
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this.toString());
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
