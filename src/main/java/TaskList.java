import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    static ArrayList<Task> tasks = new ArrayList<>();
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Prints the input string with horizontal lines above and below it
     *
     * @param string the input string
     */
    public static void printWithIndent(String string) {
        System.out.println(horizontalLines);
        System.out.println(indent + string);
        System.out.println(horizontalLines);
    }
    /**
     * @return the last Task from the taskArray
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }
    public int getSize() {
        return tasks.size();
    }
    /**
     * displays the list of Tasks
     */
    public static void displayList() {
        System.out.println(horizontalLines);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Task curr = tasks.get(i);
            System.out.println(indent + num + "." + curr.toString());
        }
        System.out.println(horizontalLines);
    }

    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the Task at position 0 at the TaskArray as 'marked'
     * After marking description, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public void markDescription(String string) {
            String clean = string.replaceAll("\\D+", ""); //remove non-digits
            int pos = Integer.parseInt(clean) - 1;
            Task curr = tasks.get(pos);

            if (string.contains("unmark")) {
                curr.markAsUnDone();
                System.out.println(horizontalLines);
                System.out.println(indent + "OK, I've marked this task as not done yet:");
            } else if (string.contains("mark")) {
                curr.markAsDone();
                System.out.println(horizontalLines);
                System.out.println(indent + "Nice! I've marked this task as done:");
            }
            System.out.println(megaIndent + curr.getStatusIconWithBracket() + " " + curr.description);
            System.out.println(horizontalLines);
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the Task at position 2 of TaskArray
     * After deleting the Task, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public void deleteTask(String string) {
        String clean = string.replaceAll("\\D+", ""); //remove non-digits
        int pos = Integer.parseInt(clean);
        if (pos > tasks.size()) {
            printWithIndent("You are trying to delete a Task that does not exist");
        } else {
            System.out.println(horizontalLines);
            System.out.println(indent + "Noted. I've removed this task:");
            System.out.println(megaIndent + tasks.get(pos - 1).toString());
            tasks.remove(pos - 1);
            System.out.println(indent + "Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(horizontalLines);
        }
    }
    public void printAllForTestingPurposes() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }
}
