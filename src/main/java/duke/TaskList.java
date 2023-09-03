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

    /**
     * Constructor for a TaskList object
     * @param data ArrayList of String that represents the data read in from the data text file by the Storage object.
     */
    public TaskList(ArrayList<String> data) {
        this.taskList = new ArrayList<>();
        for (String taskDataString : data) {
            Task task = deconstructStringIntoTask(taskDataString);
            this.taskList.add(task);
        }
    }

    /**
     * Constructor for a TaskList object where there is no data
     */
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

    /**
     * Gets the number of tasks in the task list
     * @return int that represents the number of tasks in the task list
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the Task object that matches the index
     * @param index int denoting the index of the task in the list
     * @return Task object that corresponds with the given index
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Gets a copy of the ArrayList<Task>
     * @return A clone of the ArrayList of the tasks
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> getTaskArray() {
        if (this.taskList.clone() instanceof ArrayList) {
            return (ArrayList<Task>) this.taskList.clone();
        } else {
            return new ArrayList<Task>();
        }
    }

    /**
     * Removes the Task object at the given index.
     * @param index int denoting the position of the task to be removed.
     */
    public void remove(int index) {
        this.taskList.remove(index - 1);
    }

    /**
     * Adds the Task object given to the TaskList.
     * @param task Task object that should be added to the TaskList object
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Filters the tasks in the tasklist based on the keyword
     * @param keyword String representing the keyword to use to filter down the tasks
     * @return ArrayList of tasks that have been filtered down by the keyword
     */
    public ArrayList<Task> filterTaskByKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
