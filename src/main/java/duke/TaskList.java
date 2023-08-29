package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addDeadline(String description, boolean isDone, String byDateAndTime) {
        Deadline deadline = new Deadline(description, isDone, byDateAndTime);
        tasks.add(deadline);
        Ui.showAddConfirmation(deadline, this.getTotalTasks());
    }

    public void addTodo(String description, boolean isDone) {
        ToDo todo = new ToDo(description, isDone);
        tasks.add(todo);
        Ui.showAddConfirmation(todo, this.getTotalTasks());
    }

    public void addEvent(String description, boolean isDone, String from, String to) {
        Event event = new Event(description, isDone, from, to);
        tasks.add(event);
        Ui.showAddConfirmation(event, this.getTotalTasks());
    }

    public void removeTask(int index) {

        Ui.showRemoved(tasks.get(index), this.getTotalTasks() - 1);
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
        Ui.showMarkedAsDone(tasks.get(index));
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
        Ui.showUnmarked(tasks.get(index));
    }

    public void setTasks(ArrayList<Task> taskList) {
        tasks.addAll(taskList);
    }
}
