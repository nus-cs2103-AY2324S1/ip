package duke.task;

import duke.error.DukeException;
import duke.parser.Validate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> serializedTasks) throws DukeException {
        this.tasks = new ArrayList<>();

        for (String serializedTask : serializedTasks) {
            String[] tokens = serializedTask.split(" \\| ");
            String taskType = tokens[0];
            boolean status = tokens[1].equals("1");
            String description = tokens[2];

            Task task = null;
            switch (taskType) {
                case "T": {
                    task = this.addTodo(description);
                    break;
                }
                case "D": {
                    task = this.addDeadline(description, tokens[3]);
                    break;
                }
                case "E": {
                    task = this.addEvent(description, tokens[3], tokens[4]);
                    break;
                }
            }
            if (task != null && status) {
                task.markDone();
            }
        }
    }

    private int validateIndex(int index) throws DukeException {
        if (index < 1) {
            throw new DukeException("Please provide a task index that is 1 <= task index <= 100.");
        } else if (index > this.tasks.size()) {
            throw new DukeException("The given task index is higher than current task list: "
                    + this.tasks.size() + ".");
        }

        int realIndex = index - 1;
        Task task = tasks.get(realIndex);
        if (task == null) {
            throw new DukeException("There is no task at the given task index.");
        }
        return realIndex;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        return this.tasks.remove(this.validateIndex(index));
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public Task markTask(int index) throws DukeException {
        Task task = this.tasks.get(this.validateIndex(index));
        task.markDone();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        Task task = this.tasks.get(this.validateIndex(index));
        task.unmarkDone();
        return task;
    }

    public Todo addTodo(String desc) {
        Todo todo = new Todo(desc);
        this.addTask(todo);
        return todo;
    }

    public Deadline addDeadline(String desc, String by) throws DukeException {
        Deadline deadline = new Deadline(desc, Validate.validateLocalDateTime(by));
        this.addTask(deadline);
        return deadline;
    }

    public Event addEvent(String desc, String from, String to) throws DukeException {
        Event event = new Event(desc, Validate.validateLocalDateTime(from), Validate.validateLocalDateTime(to));
        this.addTask(event);
        return event;
    }

    public void save(BufferedWriter writer) throws IOException {
        for (Task task : this.tasks) {
            writer.write(task.serialize());
            writer.newLine();
        }
    }

    public String status() {
        return String.format("You have %d tasks in the list.", this.tasks.size());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.status());
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(i)).append(
                    i < this.tasks.size() ? "\n" : "");
        }
        return str.toString();
    }
}
