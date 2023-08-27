package spot;

import java.time.LocalDate;
import java.util.ArrayList;

import spot.exception.SpotException;
import spot.task.Deadline;
import spot.task.Event;
import spot.task.Task;
import spot.task.ToDo;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ToDo addTodo(String description) {
        ToDo newTask = new ToDo(description);
        tasks.add(newTask);
        return newTask;
    }

    public Deadline addDeadline(String description, LocalDate deadline) {
        Deadline newTask = new Deadline(description, deadline);
        tasks.add(newTask);
        return newTask;
    }

    public Event addEvent(String description, LocalDate start, LocalDate end) {
        Event newTask = new Event(description, start, end);
        tasks.add(newTask);
        return newTask;
    }

    public void markTask(int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        tasks.get(position - 1).markAsDone();
    }

    public void unmarkTask(int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        tasks.get(position - 1).markAsNotDone();
    }

    public void deleteTask(int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        Task toRemove = tasks.remove(position - 1);
        System.out.println("Spot has removed this task: " + "\n" + toRemove.toString());
        System.out.println("Tasks in list: " + tasks.size());
    }

    public void listTasks(Ui ui) {
        if (tasks.size() == 0) {
            ui.showMessage("You don't have any tasks for now! Want Spot to help find some?");
        } else {
            ui.showMessage("Spot's got a list of your tasks, here!");
            for (int i = 0; i < tasks.size(); i++) {
                int position = i + 1;
                ui.showMessage(position + ". " + tasks.get(i).toString());
            }
        }
    }

    public void listTasks(Ui ui, LocalDate date) {
        if (tasks.size() == 0) {
            ui.showMessage("You don't have any tasks for now! Want Spot to help find some?");
        } else {
            ui.showMessage("Here's Spot's list of your tasks on " + date + "!\n");
            int position = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.fallsOn(date)) {
                    ui.showMessage(position + ". " + task);
                    position += 1;
                }
            }
            if (position <= 1) {
                ui.showMessage("Spot says you don't have any tasks on " + date + "!\n");
            }
        }
    }
}
