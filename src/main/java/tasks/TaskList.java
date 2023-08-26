package tasks;

import storage.Storage;
import ui.Ui;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private final int maxSize;
    protected TaskList(int maxSize) {
        this.tasks = new ArrayList<>();
        this.maxSize = maxSize;
    }

    protected TaskList(int maxSize, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.maxSize = maxSize;
    }

    public void addTask(Task newTask) {
        if (this.tasks.size() < this.maxSize) {
            this.tasks.add(newTask);
            Ui.wrapPrintWithHorizontalRules(String.format("Got it. I've added this task:\n" +
                    "  %s\nNow you have %d tasks in the list.", newTask, this.tasks.size()));
        } else {
            Ui.wrapPrintWithHorizontalRules("Your task list has reached the limit of 100 tasks. " +
                    "Please remove some tasks to proceed.");
        }
    }

    // TODO: Move this method to Ui
    public void list() {
        System.out.print(Ui.HORIZONTAL_RULE);
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, currTask);
        }
        System.out.print(Ui.HORIZONTAL_RULE + "\n");
    }

    /**
     * Factory method allows for future flexibility.
     * For instance, if they are multiple empty TaskLists,
     * we are able to use a singleton.
     *
     * @return New TaskList
     */
    public static TaskList newTaskList(int maxSize) {
        return new TaskList(maxSize);
    }

    public static TaskList taskListFromArrayList(int maxSize, ArrayList<Task> taskList) {
        return new TaskList(maxSize, taskList);
    }

    // TODO: Add IndexOutOfBoundsException handling
    // TODO: Add type mismatch exception handling
    public void markTask(int position, boolean isCompleted) {
        if (position >= 0 && position < this.tasks.size()) {
            this.tasks.get(position).toggleStatus(isCompleted);
        } else {
            Ui.wrapPrintWithHorizontalRules("Invalid position.");
        }
    }

    public void deleteTask(int position) {
        if (position >= 0 && position < this.tasks.size()) {
            Task removedTask = this.tasks.remove(position);
            Ui.wrapPrintWithHorizontalRules(String.format("Task \"%s\" removed successfully!", removedTask));
        } else {
            Ui.wrapPrintWithHorizontalRules("Invalid position.");
        }
    }

    public void listAllTasksFallingOnDate(LocalDateTime dateTime) {
        // Deadline must be within the day
        // Event can either start or end on the date itself, or both

        // Note that dateTime is at the start of day due to parsing standardisation
        // Create a copy of dateTime to represent the endOfDay
        LocalDateTime endOfDay = LocalDateTime.from(dateTime).withHour(23).withMinute(59).withSecond(59);

        StringBuilder sb = new StringBuilder("Finding the dots... to illuminate a constellation of " +
                "tasks happening today!");
        boolean hasTaskToday = false;
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            if (currTask.isOnDate(dateTime, endOfDay)) {
                sb.append("\n").append(currTask);
                hasTaskToday = true;
            }
        }
        if (!hasTaskToday) {
            sb.append("\n").append("Like a tiny dot in the sky, you're schedule is empty! ^o^");
        }
        Ui.wrapPrintWithHorizontalRules(sb.toString());
    }

    public void saveTaskListToStorage(File file) {
        Storage.saveTasks(file, this.tasks);
    }
}
