package pardiyem.task;

import java.util.ArrayList;

public class Deadline extends Task {

    protected String doBy;

    public Deadline(String description, String doBy, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (doBy.isEmpty()) {
            throw new IllegalArgumentException("Whoops, a deadline needs to have a non-empty do by description");
        }
        this.doBy = doBy;
    }

    public Deadline(String description, String doBy) {
        this(description, doBy, false);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doBy);
    }

    public static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/by");
        if (i == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the deadline by using \"/by *insert deadline*\"");
        }
        ArrayList<String> out = new ArrayList<String>();
        out.add(i == 0 ? "" : desc.substring(0, i - 1));
        out.add(desc.substring(i + 4));
        return out;
    }

}
