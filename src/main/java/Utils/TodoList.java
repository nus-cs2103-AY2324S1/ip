package Utils;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> tasks;

    protected TodoList() {
        this.tasks = new ArrayList<>();
    }

    protected Response add(String name) {
        Task task = new Task(name);
        this.tasks.add(task);
        return new InputResponse("added: " + task.name());
    }

    protected Response list() {
        InputResponse taskResponse = new InputResponse();
        int count = 0;
        for (Task task : this.tasks) {
            taskResponse.add(String.format("%d.%s",
                ++count,
                task.toString()
              ));
        }
        return taskResponse;
    }

    private boolean inRange(int idx) {
        return (this.tasks.size() > --idx);
    }

    protected Response mark(int idx) {
        InputResponse taskResponse = new InputResponse();
        if (this.inRange(idx)) {
            Task task = this.tasks.get(--idx);
            task.mark();
            taskResponse.add("Nice! I've marked this task as done:");
            taskResponse.add("  " + task.toString());
        }
        return taskResponse;
    }

    protected Response unmark(int idx) {
        InputResponse taskResponse = new InputResponse();
        if (this.inRange(idx)) {
            Task task = this.tasks.get(--idx);
            task.unmark();
            taskResponse.add("OK, I've marked this task as not done yet:");
            taskResponse.add("  " + task.toString());
        }
        return taskResponse;
    }
}
