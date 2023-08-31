package duke.data;

import duke.data.task.ToDo;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.exception.DukeException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }
    public ArrayList<Task> getTasklist() {
        return this.tasklist;
    }

    public Task markTask(int input) throws DukeException {
        if (input < 1 || (input + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.mark();
        return task;
    }

    public Task unmarkTask(int input) throws DukeException {
        if (input < 1 || (input + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        task.unmark();
        return task;
    }


    public Task deleteTask(int input) throws DukeException {
        if (input < 1 || input + 1 > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        Task task = tasklist.get(input - 1);
        tasklist.remove(input);
        return task;
    }

    public Task addTodo(String description) {
        Task newTask = new ToDo(description);
        tasklist.add(newTask);
        return newTask;
    }

    public Task addDeadline(String description, String deadline) {
        Task newTask = new Deadline(description, deadline);
        tasklist.add(newTask);
        return newTask;
    }

    public Task addEvent(String description, String from, String to) {
        Task newTask = new Event(description, from, to);
        tasklist.add(newTask);
        return newTask;
    }
}
