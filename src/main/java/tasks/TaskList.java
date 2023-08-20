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

    public void addItem(String item) {
        if (this.tasks.size() < this.maxSize) {
            Ui.wrapPrintWithHorizontalRules(String.format("added: %s", item));
            this.tasks.add(new Task(item));
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
            System.out.printf("%d[%c] %s\n", i + 1,
                    currTask.getStatus(), currTask.getDescription());
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
