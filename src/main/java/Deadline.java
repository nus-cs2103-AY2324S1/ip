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
        String subString = Description.substring(index + 4);
        String time;
        if (subString.contains(" ")) {
            int indexOfSpace = subString.indexOf(" ");
            time = TimeProcessor.StringToDate(subString.substring(0, indexOfSpace));
            time = time + subString.substring(indexOfSpace);
        } else {
            time = TimeProcessor.StringToDate(Description.substring(index + 4));
        }
        time = Description.substring(index + 1, index + 3) + ": " + time;
        this.Description = content + "(" + time + ")";
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    public Deadline(String content, boolean isDone) {
        super(content);
        this.isDone = isDone;
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
