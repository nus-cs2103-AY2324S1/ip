import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public static Event create(String[] queries) throws DukeException {
        List<String> queryList = Arrays.asList(queries);
        if (queryList.size() < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String name = "";
        String from = "", to = "";
        int fromIndex = (int) 1e9, toIndex = (int) 1e9;
        for (int i = 1; i < queryList.size(); i++) {
            if (queryList.get(i).equals("/from")) {
                fromIndex = i;
                continue;
            }
            if (queryList.get(i).equals("/to")) {
                toIndex = i;
                continue;
            }
            if (i < fromIndex) {
                name += i > 1 ? " " : "";
                name += queryList.get(i);
            } else if (i < toIndex) {
                from += i > fromIndex + 1 ? " " : "";
                from += queryList.get(i);
            } else {
                to += i > toIndex + 1 ? " " : "";
                to += queryList.get(i);
            }
        }
        return new Event(name, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String exportToText() {
        return String.format("event %s /from %s /to %s", super.exportToText(), this.from, this.to);
    }
}
