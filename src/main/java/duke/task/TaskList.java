package duke.task;

import duke.exception.InvalidIndexException;
import duke.message.AddTaskMessage;
import duke.message.MarkTaskMessage;
import duke.message.DeleteTaskMessage;
import duke.message.TaskListMessage;
import duke.message.UnmarkTaskMessage;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index < list.size();
    }

    public AddTaskMessage add(Task item) {
        this.list.add(item);
        return new AddTaskMessage(item, this.list.size());
    }

    public DeleteTaskMessage delete(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.remove(index);
        return new DeleteTaskMessage(task, this.list.size());
    }

    public TaskListMessage printList() {
        return new TaskListMessage(list);
    }

    public MarkTaskMessage markTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        return new MarkTaskMessage(task);
    }

    public UnmarkTaskMessage unmarkTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        return new UnmarkTaskMessage(task);
    }

    public String toStringStore() {
        StringBuilder sb = new StringBuilder();
        for (Task t: this.list) {
            sb.append(t.toStringStore());
            sb.append("\n");
        }
        return sb.toString();
    }
}
