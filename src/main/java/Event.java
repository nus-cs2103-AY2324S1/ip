public class Event extends Task {

    private static String parseEvent(String task) throws DukeException {
        // index 0: task name, index 1: task time range
        String [] taskSplit = task.split("/from", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name");
        }

        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to start /from)");
        }

        String taskTimeRange = taskSplit[1];
        String [] parseTimeRange = taskTimeRange.split("/to", 2);

        if (parseTimeRange.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to have /by to clarify the end date)");
        }
        if (parseTimeRange[1].trim().isEmpty()) {
            throw new DukeException("Please enter valid end date");
        }
        String eventInfoString = taskSplit[0] + "(from:" + parseTimeRange[0] +
                "to:" + parseTimeRange[1] + ")";

        return eventInfoString;
    }
    Event(String task) throws DukeException {
        super(parseEvent(task));
    }

    Event(String task, boolean isDone) {
        super(task, isDone);
    }
    @Override
    public Event done() {
        return new Event(super.getTask(), true);
    }
    @Override
    public Event undone() {
        return new Event(super.getTask(), false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
