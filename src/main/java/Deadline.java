import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private LocalDate deadline;
    private static String emptyDate = "Please specify deadline date using /by e.g. deadline report /by 2023-12-31";
    private static String invalidDate = "Please provide date with the following format: YYYY-MM-DD";

    Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public static Deadline create(String[] queries) throws DukeException {
        List<String> queryList = Arrays.asList(queries);
        if (queryList.size() < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String name = "";
        String deadlineString = "";
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
                deadlineString += i > byIndex + 1 ? " " : "";
                deadlineString += queryList.get(i);
            }
        }
        if (deadlineString.equals("")) {
            throw new DukeException(emptyDate);
        }
        LocalDate deadline = null;
        try {
            deadline = LocalDate.parse(deadlineString);
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
        return new Deadline(name, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), 
            this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("deadline %s /by %s", super.exportToText(), this.deadline);
    }
}
