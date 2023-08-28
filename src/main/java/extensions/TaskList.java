package extensions;

import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.InvalidIndexException;

public class TaskList {
    protected static final DateTimeFormatter DATETIME_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task addTodo(String desc, int isMarked) {
        Task task = new TodoTask(desc, isMarked);
        this.list.add(task);
        return task;
    }

    public Task addDeadline(String desc, String deadline, int isMarked)
            throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(deadline, DATETIME_FORMAT);
        Task task = new DeadlineTask(desc, dateTime, isMarked);
        this.list.add(task);
        return task;
    }

    public Task addEvent(String desc, String start, String end, int isMarked)
            throws DateTimeParseException {
        LocalDateTime dateTimeStart = LocalDateTime.parse(start, DATETIME_FORMAT);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(end, DATETIME_FORMAT);
        Task task = new EventTask(desc, dateTimeStart, dateTimeEnd, isMarked);
        this.list.add(task);
        return task;
    }

    public Task markTaskAsDone(int i) {
        if (this.list.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.list.size()) {
            throw new InvalidIndexException(this.list.size());
        }
        Task task = this.list.get(i-1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int i) {
        if (this.list.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.list.size()) {
            throw new InvalidIndexException(this.list.size());
        }
        Task task = this.list.get(i-1);
        task.unmark();
        return task;
    }

    public Task deleteTask(int i) {
        if (this.list.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.list.size()) {
            throw new InvalidIndexException(this.list.size());
        }
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
