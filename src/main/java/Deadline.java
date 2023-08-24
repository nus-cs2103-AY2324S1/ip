public class Deadline extends Task{

    public Deadline(String Description) {
        super(Description);

        int index = Description.indexOf("/");
        String content = Description.substring(0, index);
        String time = Description.substring(index + 1, index + 3)
                + ":"
                + Description.substring(index + 3);
        this.Description = content + "(" + time + ")";
        System.out.println("    " + this.toString());
    }

    public String toString() {
        return "[D] " + super.toString();
    }
}
