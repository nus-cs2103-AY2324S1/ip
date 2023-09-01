package duke;

import java.time.LocalDate;
import java.util.ArrayList;

// contains the tasks list, it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // add task in parameter into the arraylist of tasks in this instance
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // delete the task at index taskNum of the arraylist of tasks in this instance
    // return the deleted task if successfully deleted
    public Task deleteTask(int taskNum) {
        return this.tasks.remove(taskNum);
    }

    // mark task at index taskNum as done
    // returns false if index out of bounds else true.
    public Task doTask(int taskNum) {
        Task task = this.tasks.get(taskNum);
        task.doTask();
        return task;
    }

    // mark task at index taskNum as not done
    // returns false if index out of bounds else true.
    public Task undoTask(int taskNum) {
        Task task = this.tasks.get(taskNum);
        task.undoTask();
        return task;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String saveToStorage() {
        StringBuilder content = new StringBuilder();
        for (Task task : this.tasks) {
            content.append(task.toSaveFormat());
            content.append(System.lineSeparator());
        }
        return content.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append("    " + (i + 1) + ". " + this.tasks.get(i));
            if (i != this.tasks.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    // takes in date input and output the list of tasks on that date (in the form of
    // taskList's toString())
    // No todos will be printed, since there is no dates
    // Events will be printed only if the input date is within the date range of Event
    // Deadline will be printed if the deadline is the same as date input
    public String findTasksOnDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                sb.append("    " + (i + 1) + ". " + this.tasks.get(i));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

}
