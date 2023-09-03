package taskmate.tools;

import taskmate.exceptions.NoDataException;
import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks = new ArrayList<Task>();
    int numTotalTasks = 0;
    int numIncompleteTasks = 0;

    public TaskList() {
    }

    public TaskList(String contentsFromDisk) throws NoDataException {
        if (contentsFromDisk.isEmpty()) {
            throw new NoDataException();
        }

        String[] lines = contentsFromDisk.split("\\n");
        String taskType, name, by, from, to, delimiter, delimiter2;
        boolean taskIsDone;
        Task newTask;
        for (String line: lines) {
            taskType = line.substring(1,2);
            taskIsDone = line.charAt(4) == 'X';

            if (taskType.equals("T")) {
                // To-do task
                name = line.substring(7);
                newTask = new Todo(name, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else if (taskType.equals("D")) {
                // Deadline
                delimiter = "(by: ";
                int indexOfByParam = line.lastIndexOf(delimiter);
                name = line.substring(7, indexOfByParam);
                by = line.substring(indexOfByParam + delimiter.length(), line.length() - 1);
                newTask = new Deadline(name, by, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else if (taskType.equals("E")) {
                // Event
                delimiter = "(from: ";
                delimiter2 = " to: ";
                int indexOfFromParam = line.lastIndexOf(delimiter);
                int indexOfToParam = line.lastIndexOf(delimiter2);
                name = line.substring(7, indexOfFromParam);
                from = line.substring(indexOfFromParam + delimiter.length(), indexOfToParam);
                to   = line.substring(indexOfToParam + delimiter2.length(), line.length() - 1);
                newTask = new Event(name, from, to, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else {
                // Invalid event
                System.out.println("Invalid task: " + line);
            }

            System.out.println("Found and loaded saved task: " + line);
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        numTotalTasks++;
        numIncompleteTasks++;
    }

    public void addTask(Task t, boolean isDone) {
        this.addTask(t);
        if (!isDone) {
            return;
        }
        try {
            this.markAsDone(t);
        } catch (TaskNotFoundException e) {
            System.out.println(e); // todo: Assert that t can be found within tasks so that can remove try catch
        }
    }

    public void removeTask(int i) throws TaskNotFoundException {
        if (i > tasks.size() | i < 0) {
            throw new TaskNotFoundException();
        }
        Task t = tasks.get(i);
        tasks.remove(i);
        if (t.getIsDone()) {
            numIncompleteTasks--;
        }
        numTotalTasks--;
    }

    public void markAsDone(Task t) throws TaskNotFoundException {
        if (!this.tasks.contains(t)) {
            throw new TaskNotFoundException();
        }

        if (!t.getIsDone()) {
            t.markAsDone();
            numIncompleteTasks--;
        }
    }

    public void markAsNotDone(Task t) throws TaskNotFoundException {
        if (!this.tasks.contains(t)) {
            throw new TaskNotFoundException();
        }
        if (t.getIsDone()) {
            t.markAsNotDone();
            numIncompleteTasks++;
        }
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getNumTotalTasks() {
        return this.numTotalTasks;
    }

    public int getNumIncompleteTasks() {
        return this.numIncompleteTasks;
    }

    public Task getTask(int i) throws TaskNotFoundException {
        if (i >= tasks.size() | i < 0) {
            throw new TaskNotFoundException();
        }
        return tasks.get(i);
    }

    public String formatAllTasksForSaving() {
        String returnString = "";
        for (Task t : getAllTasks()) {
            returnString += t.formatTaskForSaving();
            returnString += "\n";
        }
        return returnString;
    }
}
