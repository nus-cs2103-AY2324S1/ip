package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.ui.Ui;

import java.util.ArrayList;
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
        return removedTask;
    }

    /**
     * Clears entire list, only when file is corrupt.
     */
    public void clearAll() {
        taskList.clear();
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
            return "list looks empty to me!";
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
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    /**
     * Handles the unmark/mark command input by user
     *
     * @param command mark or unmark
     * @param tokeniser number for which task is to be mark/unmark
     * @return String of whether mark/unmark was succesful or if all task is complete
     * @throws IllegalCommandExceptiontask if command is in invalid format
     */
    public String changeMark(String command, Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > getNumberOfTask()) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                if (command.equals("mark")) {
                    String message = markDone(id - 1);
                    if (isAllComplete()) {
                        message += list();
                    }
                    return message;
                } else {
                    return markNotDone(id - 1);
                }
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    /**
     * Handles the main logic of delete task command input by user
     *
     * @param tokeniser input of user behind delete command
     * @return Message if task has been successful or not and if all task are complete
     * @throws IllegalCommandException invalid format of command
     */
    public String deleteTask(Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > getNumberOfTask()) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                Task removedTask = remove(id - 1);
                String message = "Happily scratched this off your list:\n"
                        + Ui.indentLineBy(removedTask.toString(), 2) + "Now you have "
                        + getNumberOfTask() + " tasks in the list!";
                if (isAllComplete()) {
                    message += list();
                }
                return message;
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    private String markDone(int index) {
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

    private String markNotDone(int index) {
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

    /**
     * Utility method to check if str is an integer input
     *
     * @param str input
     * @return true if integer, false if not
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
