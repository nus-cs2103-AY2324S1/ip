package chad.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTodo(String description) {
        tasks.add(new ToDo(description));
    }

    public void addDeadline(String description, LocalDateTime dueDate) {
        tasks.add(new Deadline(description, dueDate));
    }

    public void addEvent(String description, LocalDateTime start, LocalDateTime end) {
        tasks.add(new Event(description, start, end));
    }

    public String markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark();
            return "Yessir! This task is marked as done:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    public String markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.unMark();
            return "Aite, I've marked this task as not done yet:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    public String displayTasks() {
        StringBuilder sb = new StringBuilder("Here's your tasks m8:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i).toString()).append("\n");
        }
        sb.append("Now you have " + tasks.size() + " tasks in your list\n");
        return sb.toString();
    }

    public String deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            return "Aite, this task is gone bro: " + task.toString();
        }
        return "Invalid task index.";
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No tasks found with that keyword.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1) + "." + matchingTasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

}
