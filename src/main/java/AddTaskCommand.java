import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {

    private final TaskType type;
    private final String[] args;

    public AddTaskCommand(TaskType type, String... args) {
        this.type = type;
        this.args = args;
    }
    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException {
        switch (this.type) {
        case TODO:
            TodoTask newTodo = new TodoTask(this.args[0]);
            taskList.addTask(newTodo);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newTodo.toString(),
                    taskList.getListLengthStatus()
                    );
            break;
        case DEADLINE:
            LocalDate deadline;
            try {
                deadline = Parser.parseDate(this.args[1]);
            } catch (DateTimeParseException e) {
                throw new DuckyInvalidCommandFormatException(
                        "Your deadline should be in yyyy-mm-dd format."
                );
            }
            DeadlineTask newDeadline = new DeadlineTask(this.args[0], deadline);
            taskList.addTask(newDeadline);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newDeadline.toString(),
                    taskList.getListLengthStatus()
            );
            break;
        case EVENT:
            EventTask newEvent = new EventTask(this.args[0], this.args[1], this.args[2]);
            taskList.addTask(newEvent);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newEvent.toString(),
                    taskList.getListLengthStatus()
            );
            break;
        }
    }
}
