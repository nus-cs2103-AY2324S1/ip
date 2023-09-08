package peko.tasks;

import peko.DateTimeHandler;
import peko.exceptions.InvalidTaskException;

public class Event extends Task {
    private char type = 'E';
    private DateTimeHandler startHandler;
    private DateTimeHandler endHandler;
    public Event(String s) throws InvalidTaskException {
        super(s);
        String[] split = s.split(" /from ");
        if (split.length == 1) {
            System.out.println("There's no start date peko!");
            return;
        } else if (split.length >= 3) {
            System.out.println("You can't have two start dates peko!");
            return;
        }

        split[0].split(" ", 2);
        this.name = split[0];
        split = split[1].split(" /to ");
        if (split.length == 1) {
            System.out.println("There's no end date peko!");
            return;
        } else if (split.length >= 3) {
            System.out.println("You can't have two end dates peko!");
            return;
        }
        startHandler = new DateTimeHandler(split[0]);
        endHandler = new DateTimeHandler(split[1]);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + startHandler.stringDisplay()
                + " to: " + endHandler.stringDisplay() + ")";
    }

    public String toStore() {
        String curr = this.status ? "0" : "1";
        return "E" + " | " + curr + " | " + this.name + " | " + startHandler.toString() + " | " + endHandler.toString();
    }
}
