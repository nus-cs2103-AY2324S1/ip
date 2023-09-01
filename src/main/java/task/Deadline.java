package task;

import dukeutilities.TimeFormatter;

public class Deadline extends Task {
    private String title;
    private TimeFormatter deadline;

    public Deadline(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        int info = response.indexOf("/");
        this.title = response.substring(toTrim + 1, info - 1);
        this.deadline = new TimeFormatter(response.substring(info + 4));
    }

    @Override
    public Boolean compareTitle(String query) {
        return this.title.contains(query);
    }

    @Override
    public String toFileString() {
        if (this.done == true) {
            return "D | 1 | " + this.title + " | " + this.deadline.formatDate();
        }
        return "D | 0 | " + this.title + " | " + this.deadline.formatDate();
    }

    @Override
    public String toString() {
        String s = String.format("| DUE: %s |", this.deadline.formatDate());
        if (this.done == true) {
            return "[D] " + "[X] " + this.title + " " + s;
        }
        return "[D] " + "[ ] " + this.title + " " + s;
    }

}


