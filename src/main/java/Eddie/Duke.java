package Eddie;

import java.util.ArrayList;

/**
 * The central control point for the chatbot Eddie.
 */
public class Duke {
    /**
     * Exits the chatbot.
     */
    public static void exit() {
        Ui.exit();
        System.exit(0);
    }


    /**
     * Adds a task to the Tasklist.
     * @param t the task to be added to Tasklist.
     */
    public static void add(Task t){
        String taskName = t.getName();
        TaskList.add(t);
        Ui.addTask(t.toString(), TaskList.size());

        Storage.write();


    }

    /**
     * Clears the Tasklist.
     */
    public static void clear() {
        TaskList.clear();
        Ui.clear();

        Storage.write();
    }


    /**
     * Prints out a list of current Tasks stored in the Tasklist.
     */
    public static void list() {
        int listSize = TaskList.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = TaskList.get(i);
            Ui.listTask(num, taskToList.toString());
        }
    }

    /**
     * Deletes a task stored in the Tasklist.
     * @param num the Task number which you want to delete.
     */
    public static void delete(int num) {
        Task t = TaskList.get(num - 1);
        TaskList.delete(num - 1);

        Ui.removeTask(t.toString(), TaskList.size());
        Storage.write();

    }

    /**
     * Main class to be run
     * @param args NA
     */
    public static void main(String[] args) {

        TaskList.clear();
        Storage.readFile();

        Ui.welcome();
        Parser.parse();
    }
}
