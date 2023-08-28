public class Deadline extends Task{

    public Deadline(String Description) throws DukeNoDescriptionException,
            DukeNoDateException{
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Deadline");
        }
        int index = Description.indexOf("/");
        if (index == -1) {
            throw new DukeNoDateException("Deadline");
        }
        String content = Description.substring(9, index);
        String time = Description.substring(index + 1, index + 3)
                + ":"
                + Description.substring(index + 3);
        this.Description = content + "(" + time + ")";
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
