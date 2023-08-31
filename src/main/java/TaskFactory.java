import exception.TaskParseException;

public class TaskFactory {
    public TaskFactory() {
    }

    public Task createTask(String taskType, String taskName, String[] args) throws TaskParseException {
        if (taskName.isEmpty()) {
            throw new TaskParseException("usage: TaskType(todo | deadline | event) " +
                    "TaskName " +
                    "[/StartDate] [/EndDate]");
        }

        // Safe to get the TaskType enum without checking for errors, because the only way for
        // createTask to be called is when todo | deadline | event is parsed.
        TaskType typeEnum = TaskType.valueOf(taskType.toUpperCase());
        String startDate;
        String endDate;
        switch (typeEnum) {
            case TODO:
                return new TodoTask(taskName);
            case DEADLINE:
                if (args.length != 1) {
                    throw new TaskParseException(String.format(":< deadline Tasks must have an end date%n" +
                            "usage: deadline taskName /10 May 2023"));
                }
                endDate = args[0].trim();
                return new DeadlineTask(taskName, endDate);
            case EVENT:
                if (args.length != 2) {
                    throw new TaskParseException(String.format(":< event Tasks must have a start and end date%n" +
                            "usage: event taskName /10 May 2023 /20 May 2023"));
                }
                startDate = args[0].trim();
                endDate = args[1].trim();
                return new EventTask(taskName, startDate, endDate);
            default:
                // should never reach here as explained above.
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }
}
