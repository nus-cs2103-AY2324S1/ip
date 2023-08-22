public class Deadline extends Task {

    private static String parseDeadline(String task) throws DukeException {
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

        String deadlineInfoString = taskSplit[0] + "(by: " + dueDate + ")";
        return deadlineInfoString;
    }

    Deadline(String task) throws DukeException {
        super(parseDeadline(task));
    }

    Deadline(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public Deadline done() {
        return new Deadline(super.getTask(), true);
    }
    @Override
    public Deadline undone() {
        return new Deadline(super.getTask(), false);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
