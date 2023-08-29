public class Event extends Task {

    String startDatetime;
    String endDatetime;

    Event(String name, String startDatetime, String endDatetime) {
        super(name);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    String getTaskType() {
        return "Event";
    }

    String getStartDatetime() {
        return this.startDatetime;
    }

    String getEndDatetime() {
        return this.endDatetime;
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetime() + " to: " + getEndDatetime() + ")";
    }


}
