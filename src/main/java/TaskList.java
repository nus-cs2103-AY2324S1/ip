import javax.xml.crypto.Data;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    public TaskList(DataStorage store) {
        taskList = store.taskData;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.remove(taskIndex);
        }
    }

    public void mark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsDone();
        }
    }

    public void unmark(int taskIndex) throws DukeException {
        if (taskIndex >= (taskList.size()) || taskIndex < 0) {
            throw new DukeException("There are only " + taskList.size() + " tasks");
        } else {
            taskList.get(taskIndex).markAsUndone();
        }
    }

}
