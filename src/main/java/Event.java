public class Event extends Task {
    private String title;
    private String start;
    private String end;

    public Event(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        int info = response.indexOf("/");
        this.title = response.substring(toTrim + 1, info - 1);
        String startingEnding = response.substring(info + 1);
        int endTime = startingEnding.indexOf("/");
        this.start = startingEnding.substring(5, endTime - 1);
        this.end = startingEnding.substring(endTime + 4);
    }

    @Override
    public String toFileString() {
        if (this.done == true) {
            return "D | 1 | " + this.title + " | " + this.start + "-" + this.end;
        }
        return "D | 0 | " + this.title + " | " +  this.start + "-" + this.end;
    }

    @Override
    public String toString() {
        String s = String.format("(from: %s to: %s)", start, end);
        if (this.done == true) {
            return "[E] " + "[X] " + this.title + " " + s;
        }
        return "[E] " + "[ ] " + this.title + " " + s;
    }
}


