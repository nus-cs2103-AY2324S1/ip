package duke;

import java.util.ArrayList;
public class TaskList {

    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static ArrayList<Task> keywordList = new ArrayList<>();

    /**
     * Method to add a new task into the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Method to delete the task from the list
     *
     * @param index the index of the task to be removed
     */
    public void deleteTask(int index) {
        Task removedTask = listOfTasks.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * List out the current task list when the command word is entered
     */
    public void listOutTasks() {
        if (listOfTasks.isEmpty()) {
            System.out.println("There is currently no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println((i + 1) + "." + listOfTasks.get(i));
            }
        }
    }

    /**
     * handle the mark done request from user. Call the markDone() method from the task class
     *
     * @param i the index of the task to be mark as done
     */
    public void markAsDone(int i) {
        listOfTasks.get(i - 1).markDone();
        System.out.println("Nice! I've marked this task as done:\n " + listOfTasks.get(i - 1));
    }

    /**
     * handle the mark undone request from user. Call the markDAsUndone() method from the task class
     *
     * @param i the index of the task to be mark as undone
     */
    public void markAsUndone(int i) {
        listOfTasks.get(i - 1).markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n " + listOfTasks.get(i - 1));
    }

    /**
     * return the number of task currently in the task list
     *
     * @return the number of task in the list
     */
    public int numOfTasks() {
        return listOfTasks.size();
    }

    /**
     * return the list of task
     * @return the current list of task
     */
    public static ArrayList<Task> getList() {
        return listOfTasks;
    }

    /**
     * Search for the keyword and add it into the list of task that also contains the keyword
     *
     * @param keyword the keyword to search for
     */
    public static void findKeyword(String keyword) {
        keywordList.clear();
        for (Task task : listOfTasks) {
            if (task.getTask().contains(keyword)) {
                keywordList.add(task);
            }
        }
    }

    /**
     * Getter to get the list of task with the keyword
     *
     * @return the list of task containing the keyword
     */
    public static ArrayList<Task> getKeywordList() {
        return keywordList;
    }
}