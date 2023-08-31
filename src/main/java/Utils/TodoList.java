package Utils;

import java.util.ArrayList;

public class TodoList {
    ArrayList<Task> tasks;

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
            taskResponse.add(String.format("%d. %s", ++count, task.name()));
        }
        return taskResponse;
    }
}
