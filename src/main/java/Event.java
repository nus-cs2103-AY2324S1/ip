import java.util.ArrayList;

public class Event extends Task {

    protected String startDateTime;
    protected String endDateTime;
    public Event (String description) {
        super(description.split("/from")[0]);
        String thing = description.split("/from")[0];
        this.startDateTime = description.split("/from")[1].split("/to")[0];
        this.endDateTime = description.split("/to")[1];

    }


    public String type() {
        return "E";
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s /from %s /to %s", this.type(), this.isDone, this.description, this.startDateTime, this.endDateTime);
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(from:%s to:%s)", type, super.toString(), this.startDateTime, this.endDateTime );
    }
}
