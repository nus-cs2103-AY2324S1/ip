package duke.task;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;

public class TaskList {
    public ArrayList<Task> taskArray = new ArrayList<>();

    public TaskList() {}

    public TaskList(Stream<String> taskDataStream) {
        taskDataStream.forEach(taskData -> {
            try {
                Parser.parseTaskDataString(taskData).execute(this, null, null);
            } catch (DukeException e) {

            }
        });
    }

    public int size() {
        return this.taskArray.size();
    }

    public Stream<Task> getTasks() {
        return this.taskArray.stream();
    }

    public void addTask(Task task) {
        taskArray.add(task);
    }

    public Task markAsDone(int taskIndex) throws DukeException {
        try {
            return taskArray.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    public Task markAsUndone(int taskIndex) throws DukeException {
        try {
            return taskArray.get(taskIndex).markAsUndone();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        try {
            return taskArray.remove(taskIndex).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    public void storeTasks() throws DukeException {
        Storage.writeFile("tasks.txt", taskArray.stream().map(task -> task.getDataString()));
    }
}