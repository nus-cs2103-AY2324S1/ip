package duke;

import java.util.ArrayList;

/**
 * Contains the task list with operations such as
 * add or delete to alter the list
 */
public class TaskList {

    ArrayList<Task> list;
    int count;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.count = list.size();
    }

    /**
     * Prints the list.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Add tasks to the list.
     *
     * @param task Task to be added into the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }

    /**
     * Delete tasks from the list.
     *
     * @param index Index of the item to be removed from the list.
     */
    public void deleteTask(int index) {
        this.list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index));
        count--;
        System.out.println("Now you have " + count + " tasks in the list");
    }

    /**
     * Gets the task from the list.
     *
     * @param index Index of item to be accessed
     * @return Task to be accessed
     */
    public Task getTask(int index) {
        for (int i = 0; i < count; i++) {
            if (index == i) {
                return list.get(i);
            }
        }
        return null;
    }

    public void findTask(String input, TaskList list) {
        int[] indexList = new int[100];
        int counter = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < count; i++) {
            if (list.getTask(i).toString().contains(input)) {
                indexList[counter] = i;
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("Oops, there are no matching tasks in your list");
        } else {
            for(int j = 0; j < counter; j++) {
                System.out.println((indexList[j] + 1) + "." + list.getTask(indexList[j]).toString());
            }
        }
    }
}
