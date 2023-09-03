package spot;

import java.time.LocalDate;
import java.util.ArrayList;

import spot.exception.SpotException;
import spot.task.Deadline;
import spot.task.Event;
import spot.task.Task;
import spot.task.ToDo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new Tasklist object with pre-existing ArrayList of Tasks.
     *
     * @param tasks Pre-existing ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds new ToDo object to the TaskList.
     *
     * @param description Description of the new Todo.
     * @return The ToDo added to the TaskList.
     */
    public ToDo addTodo(String description) {
        ToDo newTask = new ToDo(description);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds new Deadline object to the TaskList.
     *
     * @param description Description of the new Deadline.
     * @param deadline Due date of the new Deadline.
     * @return The Deadline added to the TaskList.
     */
    public Deadline addDeadline(String description, LocalDate deadline) {
        Deadline newTask = new Deadline(description, deadline);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds new Event object to the TaskList.
     *
     * @param description Description of the new Event.
     * @param start Start date of the new Event.
     * @param end End date of the new Event.
     * @return The Event added to the TaskList.
     */
    public Event addEvent(String description, LocalDate start, LocalDate end) {
        Event newTask = new Event(description, start, end);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Marks Task at the specified position as done.
     *
     * @param position Specified position.
     * @throws SpotException  If the task at the specified position does not exist.
     */
    public void markTask(Ui ui, int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        tasks.get(position - 1).markAsDone(ui);
    }

    /**
     * Marks Task at the specified position as not done.
     *
     * @param position Specified position.
     * @throws SpotException  If the task at the specified position does not exist.
     */
    public void unmarkTask(Ui ui, int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        tasks.get(position - 1).markAsNotDone(ui);
    }

    /**
     * Deletes Task at the specified position.
     *
     * @param position Specified position.
     * @throws SpotException  If the task at the specified position does not exist.
     */
    public void deleteTask(Ui ui, int position) throws SpotException {
        if (position < 0 || position > tasks.size()) {
            throw new SpotException("Spot thinks that task doesn't exist!");
        }
        Task toRemove = tasks.remove(position - 1);
        ui.setMessage("Spot has removed this task: " + "\n" + toRemove.toString());
        ui.setMessage("Tasks in list: " + tasks.size());
    }

    /**
     * Lists all tasks in the current TaskList.
     *
     * @param ui Current Ui object.
     */
    public void listTasks(Ui ui) {
        if (tasks.size() == 0) {
            ui.setMessage("You don't have any tasks for now! Want Spot to help find some?");
        } else {
            ui.setMessage("Spot's got a list of your tasks, here!");
            for (int i = 0; i < tasks.size(); i++) {
                int position = i + 1;
                ui.setMessage(position + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Lists all tasks in the current TaskList that fall on a specified date.
     *
     * @param ui Current Ui object.
     * @param date Specified date.
     */
    public void listTasks(Ui ui, LocalDate date) {
        if (tasks.size() == 0) {
            ui.setMessage("You don't have any tasks for now! Want Spot to help find some?");
        } else {
            int position = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.fallsOn(date)) {
                    ui.setMessage(position + ". " + task);
                    position += 1;
                }
            }
            if (position <= 1) {
                ui.setMessage("Spot says you don't have any tasks on " + date + "!\n");
            }
        }
    }

    /**
     * Lists all tasks in the current TaskList that match the specified keyword.
     *
     * @param ui Current Ui object.
     * @param keyword Specified keyword.
     */
    public void findTasks(Ui ui, String keyword) {
        if (tasks.size() == 0) {
            ui.setMessage("You don't have any tasks for now! Want Spot to help find some?");
        } else {
            int position = 1;
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.descriptionContains(keyword)) {
                    ui.setMessage(position + ". " + task);
                    position += 1;
                }
            }
            if (position <= 1) {
                ui.setMessage("Spot can't find any tasks matching the keyword: " + keyword + "\n");
            }
        }
    }
}
