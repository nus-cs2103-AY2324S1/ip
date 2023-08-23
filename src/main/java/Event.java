public class Event extends Task {

    protected String startDateTime;
    protected String endDateTime;
    public Event (String description) {
        super(description.split("/from")[0]);
        this.startDateTime = description.split("/from")[1].split("/to")[0];
        this.endDateTime = description.split("/to")[1];
    }

    public String type() {
        return "E";
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(from:%s to:%s)", type, super.toString(), this.startDateTime, this.endDateTime );
    }
}
