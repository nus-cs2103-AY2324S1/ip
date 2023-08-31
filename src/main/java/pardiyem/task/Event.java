package pardiyem.task;

import java.util.ArrayList;

public class Event extends Task {
    protected String to;
    protected String from;
    public Event(String description, String from, String to, boolean isDone) throws IllegalArgumentException  {
        super(description, isDone);
        if (to.isEmpty()) {
            throw new IllegalArgumentException("Whoops, an event need to have a non-empty ending time");
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException("Whoops, an event need to have a non-empty starting time");
        }
        this.to = to;
        this.from = from;
    }

    public Event(String description, String from, String to) throws IllegalArgumentException {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    public static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/from");
        if (i == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the starting time by using \"/from *insert starting time*\"");
        }
        int j = desc.indexOf("/to");
        if (j == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the ending time by using \"/to *insert ending time*\"");
        }
        ArrayList<String> out = new ArrayList<String>();
        if (j > i) {
            out.add(i == 0 ? "" : desc.substring(0, i - 1));
            out.add(desc.substring(i + 6, j - 1));
            out.add(desc.substring(j + 4));
        } else {
            out.add(j == 0 ? "" : desc.substring(0, j - 1));
            out.add(desc.substring(i + 6));
            out.add(desc.substring(j + 4, i - 1));
        }
        return out;
    }
}
