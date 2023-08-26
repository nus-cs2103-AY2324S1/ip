package duke.task;

import duke.exception.InvalidIndexException;
import duke.message.AddTaskMessage;
import duke.message.MarkTaskMessage;
import duke.message.RemoveTaskMessage;
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
    public void add(Task item) {
        this.list.add(item);
        new AddTaskMessage(item, this.list.size()).send();
    }
    public void delete(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.remove(index);
        new RemoveTaskMessage(task, this.list.size()).send();
    }
    public void printList() {
        new TaskListMessage(list).send();
    }
    public void markTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        new MarkTaskMessage(task).send();
    }
    public void unmarkTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        new UnmarkTaskMessage(task).send();
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
