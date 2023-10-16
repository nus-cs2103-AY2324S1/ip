package ax.display;

import ax.task.TaskList;

/**
 * The UI Class for basic display elements
 */
public class Ui {

    /**
     * Displays the current to-do list.
     */
    public static String listTheListString() {
        String res = "";
        for (int i = 1; i < TaskList.getListItems().size() + 1; i++) {
            System.out.printf("%d. %s%n", i, TaskList.getListItems().get(i - 1));
            res += String.format("%d. %s%n", i, TaskList.getListItems().get(i - 1)) + "\n";
        }
        return res;
    }

    /**
     * Displays the current to-do list, but FILTERED based on the search string entered
     * @param str the string that you want to search by
     */
    public static String listTheListString(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < TaskList.getListItems().size() + 1; i++) {
            if (TaskList.getListItems().get(i - 1).toString().contains(str)) {
                System.out.printf("%d. %s%n", i, TaskList.getListItems().get(i - 1));
                res.append(String.format("%d. %s%n", i, TaskList.getListItems().get(i - 1))).append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Displays the current due items
     * @return The list of due items
     */
    public static String listDueItems() {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < TaskList.getListItems().size() + 1; i++) {
            if (TaskList.getListItems().get(i - 1).isDue()) {
                System.out.printf("%d. %s%n", i, TaskList.getListItems().get(i - 1));
                res.append(String.format("%d. %s%n, has been due since %s",
                        i, TaskList.getListItems().get(i - 1),
                        TaskList.getListItems().get(i - 1).getDueDate())).append("\n");
            }
        }
        return res.toString();
    }
}
