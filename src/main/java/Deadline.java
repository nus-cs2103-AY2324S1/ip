import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public static Deadline create(String[] queries) {
        List<String> queryList = Arrays.asList(queries);
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
}
