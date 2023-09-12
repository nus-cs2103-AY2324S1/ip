package peko.commands;

import peko.tasks.Task;
import peko.memory.StorageHandler;

/**
 * The Find class is responsible for searching and displaying tasks based on a search query.
 * It retrieves tasks from a storage handler matching the search criteria and provides a method
 * to display the results.
 */
public class Find {
    private Task[] tempTaskList;
    private String searchQuery;

    /**
     * Constructs a Find object with a specific search query.
     *
     * @param s the search query to search for tasks with.
     */
    public Find(String s) {
        System.out.println("Find: " + s);
        if (s.isEmpty()) {
            System.out.println("You're not searching for anything Peko?");
        } else {
            searchQuery = s;
            tempTaskList = new Task[100];
            search();
            //display();
        }
    }

    /**
     * Private method to search for tasks that match the provided search query.
     * It populates the {@code tempTaskList} with matching tasks.
     */
    private void search() {
        this.tempTaskList = StorageHandler.search(searchQuery);
    }

    /**
     * Displays the search results in a formatted string.
     *
     * @return A string containing the formatted search results.
     */
    public String display() {
        String out = "";
        for (Task t : tempTaskList) {
            if (t == null) {
                break;
            }
            System.out.println(t);
            out += t + "\n";
        }
        return out;
    }

}
