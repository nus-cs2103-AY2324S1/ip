package extensions;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task addTodo(String desc) {
        Task task = new TodoTask(desc);
        this.list.add(task);
        return task;
    }

    public Task addTodo(String desc, int isMarked) {
        Task task = new TodoTask(desc, isMarked);
        this.list.add(task);
        return task;
    }

    public Task addDeadline(String desc, String deadline) {
        Task task = new DeadlineTask(desc, deadline);
        this.list.add(task);
        return task;
    }

    public Task addDeadline(String desc, String deadline, int isMarked) {
        Task task = new DeadlineTask(desc, deadline, isMarked);
        this.list.add(task);
        return task;
    }

    public Task addEvent(String desc, String start, String end) {
        Task task = new EventTask(desc, start, end);
        this.list.add(task);
        return task;
    }

    public Task addEvent(String desc, String start, String end, int isMarked) {
        Task task = new EventTask(desc, start, end, isMarked);
        this.list.add(task);
        return task;
    }

    public Task markTaskAsDone(int i) {
        Task task = this.list.get(i-1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int i) {
        Task task = this.list.get(i-1);
        task.unmark();
        return task;
    }

    public Task deleteTask(int i) {
        Task task = this.list.remove(i-1);
        return task;
    }

    public int getSize() {
        return this.list.size();
    }

    public String getTextFormattedString() {
        StringBuilder str = new StringBuilder();
        for (Task task : this.list) {
            str.append(task.getTextFormattedString() + "\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.list.size();
        if (len == 0) {
            return "The task list is empty.";
        }
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i < len; i++) {
            str.append(i + ". " + this.list.get(i-1) + "\n");
        }
        str.append(len + ". " + this.list.get(len-1));
        return str.toString();
    }

}
