package chadbod;

import java.util.ArrayList;

public class TaskList {

    private static final int TASKLIST_DISPLAY_OFFSET = 1;
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task removeTask(int i) {
        return tasks.remove(i);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if (tasks.isEmpty()) {
            output = new StringBuilder("There are no tasks in your list!");
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i ++) {
                output.append(String.format("%d.%s\n", i + TASKLIST_DISPLAY_OFFSET, tasks.get(i)));
            }
        }
        return String.valueOf(output);
    }
}
