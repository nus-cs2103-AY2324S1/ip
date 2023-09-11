import java.io.IOException;
import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String taskDescription;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    public AddEventCommand(String taskDescription, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.taskDescription = taskDescription;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Add Event Command");
            Event newTask = new Event(taskDescription, fromDateTime, toDateTime);
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            ui.showAddedTask(newTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Add Event Command");
        }
    }
}
