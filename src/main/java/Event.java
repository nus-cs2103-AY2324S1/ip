public class Event extends Task{
    public Event(String Description) throws DukeNoDescriptionException, DukeNoDateException {
        super(Description);
        if (Description.split("\\s+").length == 1) {
            throw new DukeNoDescriptionException("Event");
        }
        int index = Description.indexOf("/");
        String content = Description.substring(6, index);
        String time = getTime(Description, index);
        this.Description = content + "(" + time + ")";
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + this);
    }

    private String getTime(String Description, int index) throws DukeNoDateException {
        String time;

        int lastIndex = Description.substring(index + 1).indexOf("/");
        if (lastIndex != -1) {
            lastIndex += (index + 1);
        }

        if (lastIndex == index || Description.length() - lastIndex <  7 || lastIndex == -1) {
            throw new DukeNoDateException("Event");
        }
        String startTime = Description.substring(index + 1, index + 5)
                + ":"
                + Description.substring(index + 5, lastIndex);
        String endTime = Description.substring(lastIndex + 1, lastIndex + 3)
                + ":"
                + Description.substring(lastIndex + 3);
        time = startTime + endTime;
        return time;
    }

    public String toString() {
        return "[E]" + super.toString();
    }
}
