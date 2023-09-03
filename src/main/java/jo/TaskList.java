package jo;

import java.time.LocalDate;
import java.util.ArrayList;

import jo.task.Deadline;
import jo.task.Event;
import jo.task.Task;

public class TaskList {

    protected ArrayList<Task> tList;

    public TaskList(ArrayList<Task> tasks) {
        this.tList = tasks;
    }

    public TaskList() {
        this.tList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tList.add(task);
    }

    public void deleteTask(int index) {
        tList.remove(index);
    }

    public void markTask(int index, boolean isDone) {
        Task targetTask = this.tList.get(index);
        targetTask.mark(isDone);
    }

    public ArrayList<Task> search(LocalDate deadline) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task t : this.tList) {
            if (t instanceof Deadline) {
                Deadline task = (Deadline) t;
                if (task.getDeadline().equals(deadline) && !task.getIsDone()) {
                    resultList.add(task);
                }
            } else if (t instanceof Event) {
                Event task = (Event) t;
                if (task.getDeadline().equals(deadline) && !task.getIsDone()) {
                    resultList.add(task);
                }
            }
        }
        return resultList;
    }
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task t : this.tList) {
            if (t.getDescription().contains(keyword)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    public Task getTask(int index) {
        return this.tList.get(index);
    }

    public int getSize() {
        return this.tList.size();
    }

}
