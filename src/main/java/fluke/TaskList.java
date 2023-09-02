package fluke;

import fluke.exceptions.FlukeException;
import fluke.exceptions.TaskDoesNotExistException;
import fluke.tasks.Deadline;
import fluke.tasks.Event;
import fluke.tasks.Task;
import fluke.tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> initialTasks) {
        this.listOfTasks = initialTasks;
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    public int getSize() {
        return listOfTasks.size();
    }

    private void addTask(Task task) {
        listOfTasks.add(task);
    }

    public Task addTodo(String description) throws FlukeException {
        Todo newTodo = new Todo(description);
        addTask(newTodo);
        return newTodo;
    }

    public Task addDeadline(String description, String byDate) throws FlukeException {
        Task newDeadline = new Deadline(description, byDate);
        addTask(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String description, String fromDate, String toDate) throws FlukeException {
        Task newEvent = new Event(description, fromDate, toDate);
        addTask(newEvent);
        return newEvent;
    }

    public Task deleteTask(int index) throws FlukeException {
        // check if task exists
        if (index < listOfTasks.size()) {
            Task taskToBeDeleted = listOfTasks.get(index);
            listOfTasks.remove(index);
            return taskToBeDeleted;
        } else {
            throw new TaskDoesNotExistException();
        }
    }

    public Task markTaskAsDone(int index) throws FlukeException {
        // check if task exists
        if (index < listOfTasks.size()) {
            Task task = listOfTasks.get(index);
            task.markAsDone();
            return task;
        } else {
            throw new TaskDoesNotExistException();
        }
    }

    public Task markTaskAsUndone(int index) throws FlukeException {
        // check if task exists
        if (index < listOfTasks.size()) {
            Task task = listOfTasks.get(index);
            task.markAsUndone();
            return task;
        } else {
            throw new TaskDoesNotExistException();
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            int number = i + 1;
            str += (number + "." + task);
            if (i != listOfTasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
