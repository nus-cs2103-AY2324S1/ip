package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class represents a list of tasks and is responsible for changing the task lists for example when
 * deleting/adding tasks and marking tasks as done/undone
 */
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<String> data) {
        this.taskList = new ArrayList<>();
        for (String taskDataString : data) {
            Task task = deconstructStringIntoTask(taskDataString);
            this.taskList.add(task);
        }
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private Task deconstructStringIntoTask(String line) {
        String[] array = line.split("/", 2);
        if (array[0].equals("T")) {
            String[] arrayT = line.split("/", 3);
            Todo task = new Todo(arrayT[2]);
            if (arrayT[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (array[0].equals("D")) {
            String[] arrayD = line.split("/", 4);
            LocalDateTime byDateTime = LocalDateTime.parse(arrayD[3]);
            Deadline task = new Deadline(arrayD[2], byDateTime);
            if (arrayD[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (array[0].equals("E")) {
            String[] arrayE = line.split("/", 5);
            LocalDateTime fromDateTime = LocalDateTime.parse(arrayE[3]);
            LocalDateTime toDateTime = LocalDateTime.parse(arrayE[4]);
            Event task = new Event(arrayE[2], fromDateTime, toDateTime);
            if (arrayE[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else {
            return null;
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getTaskArray() {
        if (this.taskList.clone() instanceof ArrayList) {
            return (ArrayList<Task>) this.taskList.clone();
        } else {
            return new ArrayList<Task>();
        }
    }

    public void remove(int index) {
        this.taskList.remove(index - 1);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }
}
