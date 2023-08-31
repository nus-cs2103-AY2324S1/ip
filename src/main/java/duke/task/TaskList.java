package duke.task;

import duke.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    public static class TaskIndexOutOfRange extends Exception {
        private TaskIndexOutOfRange() {
            super();
        }
    }

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task markTaskAsDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsDone();
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    public Task markTaskAsNotDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsNotDone();
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    public Task deleteTask(int index) throws TaskIndexOutOfRange {
        try {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    private static void displayTasks(ArrayList<Task> taskList, boolean isExcludingDone, LocalDate date) {
        if (isExcludingDone) {
            taskList.removeIf(Task::isDone);
        }
        if (date != null) {
            taskList.removeIf(task -> !task.containsDate(date));
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void displayTodos(boolean isExcludingDone) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof ToDo));
        TaskList.displayTasks(taskList, isExcludingDone, null);
    }

    public void displayDeadlines(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof Deadline));
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    public void displayEvents(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof Event));
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    public void displayTasks(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    public int size() {
        return this.taskList.size();
    }

    public void saveData(Storage storage) throws Storage.FileIOException {
        StringBuilder data = new StringBuilder();
        for (Task task: this.taskList) {
            data.append(task.data()).append("\n");
        }
        storage.saveData(data.toString());
    }
}
