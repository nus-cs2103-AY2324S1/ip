public class Deadline extends Task {
    private String title;
    private String deadline;

    public Deadline(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        int info = response.indexOf("/");
        this.title = response.substring(toTrim + 1, info - 1);
        this.deadline = response.substring(info + 4);
    }

    @Override
    public String toFileString() {
        if (this.done == true) {
            return "D | 1 | " + this.title + " | " + this.deadline;
        }
        return "D | 0 | " + this.title + " | " + this.deadline;
    }

    @Override
    public String toString() {
        String s = String.format("(by: %s)", deadline);
        if (this.done == true) {
            return "[D] " + "[X] " + this.title + " " + s;
        }
        return "[D] " + "[ ] " + this.title + " " + s;
    }

}


