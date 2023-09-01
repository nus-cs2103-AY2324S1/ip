package kiera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.tasktype.TaskType;


public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(Task t) {
        this.tasks.remove(t);
    }

    public Task getTaskByIndex(int i) {
        return this.tasks.get(i - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int indexOf(Task task) {
        return this.tasks.indexOf(task) + 1;
    }
    public ArrayList<Task> filterByDate(TaskType t, LocalDate d) {
        switch (t) {
        case DEADLINE:
            return this.tasks.stream()
                    .filter(task -> task.getDeadline() != null)
                    .filter(task -> task instanceof Deadline)
                    .filter(task -> task.getDeadline().equals(d))
                    .collect(Collectors.toCollection(ArrayList::new));
        case EVENT:
            return this.tasks.stream()
                    .filter(task -> task.getStartDate() != null)
                    .filter(task -> task instanceof Event)
                    .filter(task -> task.getStartDate().equals(d))
                    .collect(Collectors.toCollection(ArrayList::new));
        default:
            System.out.println("date does not exist on task type!");
        }
        return new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
