package peko.memory;

import peko.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The StorageHandler class is responsible for managing the storage and retrieval of tasks.
 * It interacts with the `SaveHandler` class to load and save tasks from/to a text file. This class
 * also provides methods for adding, marking, unmarking, searching, and deleting tasks.
 */
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
     * Reads the array of tasks and formats them as a string.
     *
     * @return A formatted string containing the tasks in the list.
     */
    public static String readArray() {
        int i = 0;
        String out = "--------------LIST-PEKO------------------\n";
        while (todoList[i] != null) {
            out += (i+1 + ". " + todoList[i] + "\n");
            i++;
        }
        if (i == 0) {
            out = ("You are FREE PEKO!!!!!\n");
        }
        return out;
    }

    /**
     * Adds a task to the array of tasks.
     *
     * @param t The task to be added.
     * @return A message indicating the task was added successfully.
     */
    public static String addToArray(Task t) {
        todoList[size] = t;
        String out = "Added: \n   " + todoList[size].toString() + "\npeko!";
        out += "You have: " + (size+1) + " tasks now peko";
        System.out.println(out);
        size++;
        SaveHandler.saveTo();
        return out;
    }

    /**
     * Marks a task as done in the array of tasks.
     *
     * @param i The index of the task to be marked as done (1-based).
     * @return A message indicating the task was marked as done successfully.
     */
    public static String setMarkArray(int i) {
        String out;
        try {
            todoList[i-1].setMark();
            out = "Marked as done peko!\n";
            out += ("    " + todoList[i-1] + "\n");
            SaveHandler.saveTo();

        } catch (NullPointerException e) {
            out = ("You don't have so many Tasks Peko!\n");
        }
        return out;
    }

    /**
     * Unmarks a task as done in the array of tasks.
     *
     * @param i The index of the task to be unmarked as done (1-based).
     * @return A message indicating the task was unmarked as done successfully.
     */
    public static String setUnmarkArray(int i) {
        String out = "";
        try {
            todoList[i-1].setUnmark();
            out += ("You haven't done this yet peko?!\n");
            out += ("    " + todoList[i-1]);
            SaveHandler.saveTo();
        } catch (NullPointerException e) {
            out = ("You don't have so many Tasks Peko!");
        }
        System.out.println(out);
        return out;

    }

    /**
     * Searches for tasks in the array of tasks that match a search query.
     *
     * @param searchQuery The search query used to match tasks.
     * @return An array of tasks that match the search query.
     */
    public static Task[] search(String searchQuery) {
        Task[] temp = todoList;
        Task[] tempOutput = new Task[100];
        int pos = 0;
        for (Task t : temp) {
            if (t == null) {
                System.out.println("break");
                break;
            }
            if (t.hasString(searchQuery)) {
                System.out.println(t);
                tempOutput[pos] = t;
                pos++;
            }
        }
        return tempOutput;
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
     * Reads and returns a text file containing copypasta text.
     *
     * @return The copypasta text read from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static String degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        String out = "";
        while (sc.hasNextLine()) {
            String temp = (sc.nextLine() + "\n");
            out += temp;
        }
        return out;
    }

}
