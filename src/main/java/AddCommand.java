import java.io.IOException;
import java.util.ArrayList;

public class AddCommand extends Command {
    private String taskType;
    public AddCommand(ArrayList<String> commandDetails, String taskType) {
        super(commandDetails);
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.taskType) {
        case "T":
            Task newTodo = new Todo(commandDetails.get(0));
            tasks.add(newTodo);
            try {
                storage.appendToFile(newTodo);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newTodo, tasks.size());
            break;
        case "D":
            Task newDeadline = new Deadline(commandDetails.get(0), commandDetails.get(1));
            tasks.add(newDeadline);
            try {
                storage.appendToFile(newDeadline);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newDeadline, tasks.size());
            break;
        case "E":
            Task newEvent = new Event(commandDetails.get(0), commandDetails.get(1), commandDetails.get(2));
            tasks.add(newEvent);
            try {
                storage.appendToFile(newEvent);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newEvent, tasks.size());
            break;
        }
    }
}
