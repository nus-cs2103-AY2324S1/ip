package actions;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
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
     * Prints out the whole list of tasks
     */
    public void chadListTask() {
        if (taskArrayList.size() == 0) {
            System.out.println("Your task list is EMPTY!");
        } else {
            System.out.println(line);
            System.out.println("Your outstanding tasks are...");
            for (int i = 0; i < taskArrayList.size(); i++) {
                System.out.println("tasks.Task " + (i + 1) + ") " + taskArrayList.get(i)); //<<<<<
            }
            System.out.println("\n" + "Get to work NOW!\n");
            System.out.println(line);

        }
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

}
