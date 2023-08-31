package rua.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private TaskList update(ArrayList<Task> tasks) {
        return new TaskList(tasks);
    }

    public TaskList add(Task task) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
        return update(currentTasks);
    }

    public TaskList delete(int index) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.remove(index - 1);
        return update(currentTasks);
    }

    public TaskList mark(int index) throws IOException {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.mark());
        return update(currentTasks);
    }

    public TaskList unmark(int index) throws IOException {
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.unmark());
        return update(currentTasks);
    }

    public String dateSearch(LocalDate date) {
        String res= "";
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).happenOnThatDate(date)) {
                res = res + Integer.toString(index) +
                        ": " + tasks.get(i).toString() + "\n";
                index += 1;
            }
        }
        return res;
    }

    public String search(String keyword) {
        String res= "";
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                res = res + Integer.toString(index) +
                        ": " + task.toString() + "\n";
                index += 1;
            }
        }
        return res;
    }


    public String saveMessage() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
