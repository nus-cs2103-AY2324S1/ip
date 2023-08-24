public class Event extends Task{
    public Event(String Description) {
        super(Description);
        int index = Description.indexOf("/");
        String content = Description.substring(0, index);
        String time;
        int lastIndex = Description.lastIndexOf("/");
        String startTime = Description.substring(index + 1, index + 5)
                + ":"
                + Description.substring(index + 5, lastIndex);
        String endTime = Description.substring(lastIndex + 1, lastIndex + 3)
                + ":"
                + Description.substring(lastIndex + 3);
        time = startTime + endTime;
        this.Description = content + "(" + time + ")";
        System.out.println("    " + this.toString());
    }

    public String toString() {
        return "[E] " + super.toString();
    }
}
