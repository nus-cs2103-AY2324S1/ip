import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadlineDate;


    private static String[] parseDeadline(String task) throws DukeException {
        String [] taskSplit = task.split("/by", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name");
        }

        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid deadline (make sure to start /by)");
        }

        String dueDate = taskSplit[1].trim();
        if (dueDate.isEmpty()) {
            throw new DukeException("Please enter valid deadline: Do not leave it empty");
        }

        return new String[] {taskSplit[0], dueDate};
    }

    Deadline(String task) throws DukeException {
        super(parseDeadline(task)[0]);
        this.deadlineDate = LocalDate.parse(parseDeadline(task)[1]);
    }

    Deadline(String task, boolean isDone, LocalDate deadlineDate) {
        super(task, isDone);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public Deadline done() {
        return new Deadline(super.getTask(), true, this.deadlineDate);
    }
    @Override
    public Deadline undone() {
        return new Deadline(super.getTask(), false, this.deadlineDate);
    }

    @Override
    public String storageText() {
        String end = deadlineDate.toString();
        return "[D]" + super.toString() + "/by " + end;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
