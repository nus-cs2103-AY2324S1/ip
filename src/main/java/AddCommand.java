import java.time.LocalDateTime;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
public class AddCommand extends Command {

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private String taskDescription;
    private TaskType taskType;
    private LocalDateTime parsedDateTime;
    private LocalDateTime parsedFromDate;
    private LocalDateTime parsedToDate;

    // Use Method Overloading for different Commands to be added

    // To do constructor
    public AddCommand(TaskType taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    // Deadline constructor
    public AddCommand(TaskType taskType, String taskDescription, LocalDateTime parsedDateTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.parsedDateTime = parsedDateTime;
    }

    // Event constructor
    public AddCommand(TaskType taskType, String taskDescription, LocalDateTime parsedFromDate, LocalDateTime parsedToDate) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.parsedFromDate = parsedFromDate;
        this.parsedToDate = parsedToDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = null;
            switch (taskType) {
                case TODO:
                    newTask = new Todo(taskDescription);
                    break;
                case DEADLINE:
                    newTask = new Deadline(taskDescription, parsedDateTime);
                    break;
                case EVENT:
                    newTask = new Event(taskDescription, parsedFromDate, parsedToDate);
                    break;
            }
            taskList.addTask(newTask);
            ui.displayAddedTask(newTask, taskList.numTasks());
            storage.saveTasks(taskList.getAllTasks()); // Save the updated task list
        } catch (Exception e) {
            ui.showErrorMessage("Error adding task.");
        }
    }
}