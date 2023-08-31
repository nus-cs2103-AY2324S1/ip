package peko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StorageHandler {
    private static Task[] todoList;
    private static int size;

    /**
     * Constructor for the StorageHandler class.
     * Initializes the todoList by loading tasks from a txt file using SaveHandler
     * and stores the size of the stored list.
     */
    public StorageHandler() {
        todoList = SaveHandler.loadFrom();
        size = SaveHandler.size();
    }
    /**
     * Reads and displays tasks from the todoList array.
     * This method iterates through the todoList array and prints each task,
     * along with its corresponding index, to the console.
     * If the todoList is empty, a special message is displayed.
     */
    public static void readArray() {
        int i = 0;
        System.out.println("--------------LIST-PEKO------------------");
        while (todoList[i] != null) {
            System.out.println(i+1 + ". " + todoList[i]);
            i++;
        }
        if (i == 0) {
            System.out.println("You are FREE PEKO!!!!!");
        }
    }

    /**
     * Adds a task to the todoList array.
     * This method adds the provided task to the next available position in the todoList array,
     * increments the size counter, displays a confirmation message, updates the task count,
     * and saves the updated list of tasks using SaveHandler.
     *
     * @param t The task to be added to the array.
     */
    public static void addToArray(Task t) {
        todoList[size] = t;
        System.out.println("Added: \n   " + todoList[size].toString() + "\npeko!");
        System.out.println("You have: " + (size+1) + " tasks now peko");
        size++;
        SaveHandler.saveTo();
    }

    /**
     * Marks a task as done in the todoList array.
     * This method sets the mark of the task at the specified index to "done",
     * displays a confirmation message along with the marked task,
     * and updates the task list by saving it using SaveHandler.
     *
     * @param i The index of the task to be marked (1-based).
     */
    public static void setMarkArray(int i) {
        try {
            todoList[i-1].setMark();
            System.out.println("Marked as done peko!");
            System.out.println("    " + todoList[i-1]);
            SaveHandler.saveTo();

        } catch (NullPointerException e) {
            System.out.println("You don't have so many Tasks Peko!");
        }
    }

    /**
     * Unmarks a task as done in the todoList array.
     * This method removes the mark from the task at the specified index,
     * displays a message indicating the task is now unmarked,
     * and updates the task list by saving it using SaveHandler.
     *
     * @param i The index of the task to be unmarked (1-based).
     */
    public static void setUnmarkArray(int i) {
        try {
            todoList[i-1].setUnmark();
            System.out.println("You haven't done this yet peko?!");
            System.out.println("    " + todoList[i-1]);
            SaveHandler.saveTo();
        } catch (NullPointerException e) {
            System.out.println("You don't have so many Tasks Peko!");
        }

    }

    /**
     * Deletes a task from the todoList array.
     * This method removes the task at the specified index by shifting subsequent tasks up,
     * updates the size counter, and saves the updated task list using SaveHandler.
     *
     * @param i The index of the task to be deleted (1-based).
     */
    public static void setDelete(int i) {
        i--;
        while (i <= size) {
            todoList[i] = todoList[i+1];
            i++;
        }
        size--;
        SaveHandler.saveTo();
    }

    /**
     * Displays content from a text file called "Copypasta.txt".
     * This method reads and prints each line of content from the specified text file.
     *
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}
