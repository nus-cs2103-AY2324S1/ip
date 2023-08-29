import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a tasklist
 */
public class DukeList {
    private ArrayList<Task> dukeList;

    public DukeList() {
        dukeList = new ArrayList<>(100);
    }



    public DukeList(ArrayList<Task> ItemList) {
        dukeList = ItemList;
    }

    /**
     * This method simply prints out an acknowledgemnt that a task has been added
     * @param newTask takes in a new task
     */
    public void printAddList(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask.toString());
        System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
    }

    /**
     * This method adds a to-do task to the tasklist
     * @param description description of the task
     */
    public void addToDo(String description) {
        ToDo newToDo = new ToDo(description);
        dukeList.add(newToDo);
        printAddList(newToDo);
    }

    /**
     * This method adds a deadline task to the tasklist
     * @param description description of the task
     */
    public void addDeadline(String description, LocalDateTime by) {
        Deadline newDeadline = new Deadline(description, by);
        dukeList.add(newDeadline);
        printAddList(newDeadline);
    }

    /**
     * This method adds an event task to the tasklist
     * @param description description of the task
     */
    public void addEvent(String description, LocalDateTime from, LocalDateTime to) {
        Event newEvent = new Event(description, from, to);
        dukeList.add(newEvent);
        printAddList(newEvent);
    }

    /**
     * This method deletes a task from the tasklist
     * @param taskNum index of the task
     */
    public void deleteTask(int taskNum) {
        Task removedItem = dukeList.remove(taskNum - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t " + removedItem.toString());
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    /**
     * Displays the current list
     */
    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = dukeList.get(i);
            System.out.println(num + ". " + currTask.toString());
        }
    }

    /**
     * Sets task as done
     * @param taskNum index of task
     */
    public void setTaskAsDone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    /**
     * Sets task as undone
     * @param taskNum index of task
     */
    public void setTaskAsUndone(int taskNum) {
        Task chosenTask = dukeList.get(taskNum - 1);
        chosenTask.setAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }

    /**
     * returns size of list
     * @return size of list
     */
    public int getSize() {
        return dukeList.size();
    }

    public ArrayList<Task> getArrayList() {
        return this.dukeList;
    }
}
