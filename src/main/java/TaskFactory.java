import exception.TaskParseException;

public class TaskFactory {
    private final OutputService outputService;

    public TaskFactory() {
        this.outputService = new OutputService();
    }

    public Task createTask(String taskType, String taskName, String[] args) {
        if (taskName.isEmpty()) {
            outputService.echo("usage: TaskType(todo | deadline | event) " +
                    "TaskName " +
                    "[/StartDate] [/EndDate]");
            throw new TaskParseException("Task Name must be provided");
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
                    outputService.echo(String.format(":< deadline Tasks must have an end date%n" +
                            "usage: deadline taskName /10 May 2023"));
                    throw new TaskParseException("Invalid args provided for deadline Task");
                }
                endDate = args[0].trim();
                return new DeadlineTask(taskName, endDate);
            case EVENT:
                if (args.length != 2) {
                    outputService.echo(String.format(":< event Tasks must have a start and end date%n" +
                            "usage: event taskName /10 May 2023 /20 May 2023"));
                    throw new TaskParseException("Invalid args provided for event Task");
                }
                startDate = args[0].trim();
                endDate = args[1].trim();
                return new EventTask(taskName, startDate, endDate);
            default:
                throw new RuntimeException();
        }
    }
}
