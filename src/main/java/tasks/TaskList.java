package tasks;

import ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private final int maxSize;
    protected TaskList(int maxSize) {
        this.tasks = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void addItem(Task newTask) {
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

    // TODO: Add IndexOutOfBoundsException handling
    // TODO: Add type mismatch exception handling
    public void markTask(int position, boolean isCompleted) {
        if (position >= 0 && position < this.tasks.size()) {
            this.tasks.get(position).toggleStatus(isCompleted);
        } else {
            Ui.wrapPrintWithHorizontalRules("Invalid position.");
        }
    }
}
