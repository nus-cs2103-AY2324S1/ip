public class Event extends Task {
    private String[] arr;
    public Event(String name, String[] arr) {
        super(name);
        this.arr = arr;
    }
    @Override
    public String toString() {
        if (!this.getDone()) {
            return  "[E][ ] " + this.getName() + "(from: " + this.arr[0] + "to: " + this.arr[1] + ")";
        } else {
            return "[E][X] " + this.getName() + "from: " + this.arr[0] + "to: " + this.arr[1] + ")";
        }
    }
}
