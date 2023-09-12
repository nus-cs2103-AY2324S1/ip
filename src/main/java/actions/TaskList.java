package actions;

import ui.Ui;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private Ui ui;
    private ArrayList<Task> taskArrayList;
    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";
    public TaskList(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
    }

    /**
     * Adds a task to the current ArrayList
     *
     * @param input the task that is to be added
     */
    public void chadAddList(Task input) {
            taskArrayList.add(input);
    }

    /**
     * Removes a list from the current ArrayList
     *
     * @param index of the task to be removed
     * @return the name of the removed task
     */
    public String chadRemoveList(int index){
        try {
            Task removed = taskArrayList.remove(index - 1);
            return removed.name;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task index! Try again!");
        }
        return null;
    }


    /**
     * Marks a task as completed
     *
     * @param index of task to be marked
     */
    public void chadMarkTask(int index) {
        taskArrayList.get(index - 1).isComplete = true;
    }

    /**
     * Unmarks a task that was previously marked
     *
     * @param index of task to be unmarked
     */
    public void chadUnmarkTask(int index) {
        taskArrayList.get(index - 1).isComplete = false;

    }

    public void chadFindTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.name.contains(keyword)) {
                matched.add(task);
            }
        }
        if (matched.isEmpty()) {
            ui.chadMatchNotFoundOutput();
        } else {
            ui.chadMatchFoundOutput(matched);

        }
    }
    public String displayChadFindTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.name.contains(keyword)) {
                matched.add(task);
            }
        }
        if (matched.isEmpty()) {
            return ui.displayChadMatchNotFoundOutput();
        } else {
            return ui.displayChadMatchFoundOutput(matched);

        }
    }

}
