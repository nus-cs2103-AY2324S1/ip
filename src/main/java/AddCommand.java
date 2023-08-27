import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class AddCommand extends Command {
    private final TaskType taskType;
    private final String taskDescription;
    private final String additionalInfo1;
    private final String additionalInfo2;

    public AddCommand(TaskType taskType, String taskDescription, String additionalInfo1, String additionalInfo2) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.additionalInfo1 = additionalInfo1;
        this.additionalInfo2 = additionalInfo2;
    }

    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            if (taskDescription.isEmpty()) {
                Ui.showHorizontalLine();
                throw new DukeException("    Unable to add new task. Task description cannot be empty.");
            }

            Task newTask;

            switch (taskType) {
                case TODO:
                    newTask = new Todo(taskDescription);
                    break;
                case DEADLINE:
                    LocalDate byDate = Parser.parseDate(additionalInfo1);
                    newTask = new Deadline(taskDescription, byDate);
                    break;
                case EVENT:
                    LocalDateTime fromDate = Parser.parseDateTime(additionalInfo1);
                    LocalDateTime toDate = Parser.parseDateTime(additionalInfo2);
                    newTask = new Event(taskDescription, fromDate, toDate);
                    break;
                default:
                    throw new DukeException("Unsupported task type.");
            }

            tasks.add(newTask);
            Ui.showHorizontalLine();
            System.out.println("    Got it. I've added this task:\n" + "     " + newTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            Ui.showHorizontalLine();
            storage.saveTasks(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
