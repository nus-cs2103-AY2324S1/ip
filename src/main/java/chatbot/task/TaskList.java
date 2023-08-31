package chatbot.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            return tasks.get(index - 1);
        }
        return null;
    }

    public int size() {
        return tasks.size();
    }


    public void markTaskDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
    }

    public void unMarkTask(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.unMark();
        }
    }

    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public Task removeTask(int index) {
        return tasks.remove(index - 1);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }

}
