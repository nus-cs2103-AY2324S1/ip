import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public static Deadline create(String[] queries) throws DukeException {
        List<String> queryList = Arrays.asList(queries);
        if (queryList.size() < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String name = "";
        String deadline = "";
        int byIndex = (int) 1e9;
        for (int i = 1; i < queryList.size(); i++) {
            if (queryList.get(i).equals("/by")) {
                byIndex = i;
                continue;
            }
            if (i < byIndex) {
                name += i > 1 ? " " : "";
                name += queryList.get(i);
            } else {
                deadline += i > byIndex + 1 ? " " : "";
                deadline += queryList.get(i);
            }
        }
        return new Deadline(name, deadline);
    }

    @Override
    public String toString() {
        if (this.deadline.equals("")) {
            return "[D]" + super.toString();
        }
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String exportToText() {
        return String.format("deadline %s /by %s", super.exportToText(), this.deadline);
    }
}
