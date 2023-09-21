package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.ui.Formatter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the TaskList used by the chatbot.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class TaskList {
    private ArrayList<Task> taskList;

    private int numberOfCompletedTasks = 0;

    /**
     * Constructor for TaskList, initialise an ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        if (task.isComplete()) {
            numberOfCompletedTasks++;
        }
        taskList.add(task);
    }

    /**
     * Removes task from the list.
     *
     * @param index Index position of the task.
     * @return Task that was removed from the list.
     */
    public Task remove(int index) {
        Task removedTask = taskList.remove(index);
        if (removedTask.isComplete()) numberOfCompletedTasks--;
        assert numberOfCompletedTasks <= getNumberOfTask():
                "completed task must always be less than or equal total number of task";
        return removedTask;
    }

    /**
     * Clears entire list, only when file is corrupt.
     */
    public void clearAll() {
        taskList.clear();
        numberOfCompletedTasks = 0;
    }

    public int getNumberOfTask() {
        return taskList.size();
    }

    /**
     * Gets task in that index.
     *
     * @param index position of task in the list.
     * @return Task in specified index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Checks if all tasks are complete.
     *
     * @return true if all complete, false if not.
     */
    public boolean isAllComplete() {
        return taskList.size() == numberOfCompletedTasks;
    }

    /**
     * String of list of task and list headers depending on
     * number of task in the list left (including
     * if all task are completed)
     *
     * @return list of task and appropriate headers
     */
    public String list() {
        if (taskList.size() == 0) {
            return "List looks empty to me!";
        } else {
            StringBuilder displayList = new StringBuilder();
            if (isAllComplete()) {
                displayList.append("Wow! You are ALL COMPLETE!!!!\n")
                        .append("TIME TO PLAY WITH MEEEEE :DDDD\n");
            } else if (taskList.size() > 10) {
                displayList.append("Seems like you have a lot of things to do...\n")
                        .append("Remember to play with me after :D\n");
            } else {
                displayList.append("Here are the things you told me to keep track of:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                displayList.append(i + 1 + ". ").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    public String markDone(int index) {
        Task task = get(index);
        if (task.isComplete()) {
            return "That was done already...\n" +
                    "are you sure you wanted to mark that?\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks++;
            return "Yay! One step closer to playing with me!\n"
                    + task.toString();
        }
    }

    public String markNotDone(int index) {
        Task task = get(index);
        if (!task.isComplete()) {
            return "Don't worry it's still not done\n"
                    + "What are you doing? Let's get it done now!\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks--;
            return "Oh no... what happened :(\n"
                    + task.toString();
        }
    }

    public String markAll() {
        for (int i = 0; i < getNumberOfTask(); i++) {
            markDone(i);
        }
        return "Marked all your task done! TIME TO PLAY?";
    }

    public String unmarkAll() {
        for (int i = 0; i < getNumberOfTask(); i++) {
            markNotDone(i);
        }
        return "Oh noooo! Unmarked everything! D:";
    }

    public String listResults(String keyword) {
        if (getNumberOfTask() == 0) {
            return "Unfortunately, I couldn't find any task matching \"" +
                    keyword +
                    "\" :(";
        } else {
            StringBuilder displayList = new StringBuilder();
            displayList.append("Here are task(s) matching \"" + keyword + "\" in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    public void modifyTask(int index, Task task) {
        if (taskList.get(index).isComplete()) numberOfCompletedTasks--;
        taskList.set(index, task);
    }
}
