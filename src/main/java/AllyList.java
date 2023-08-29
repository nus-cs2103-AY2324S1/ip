import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class AllyList {
    ArrayList<Task> arr;
    public AllyList() {
        arr = new ArrayList<>(100);
    }

    /**
     * Function to add tasks from the input into the arraylist.
     * @param str
     */
    public void addElements(String str) {
        Task task = new Task(str);
        arr.add(task);
    }

    /**
     * Function to mark the task as done.
     * @param index
     */
    public void markAsDone(int index) {
        Task task = arr.get(index);
        task.setMarked();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Function to unmark the task to undone.
     * @param index
     */
    public void unMarkDone(int index) {
        Task task = arr.get(index);
        task.notDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    /**
     * Prints the current list of tasks.
     */
    public void printElements() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0, len = arr.size(); i < len; i++) {
            System.out.println((i + 1) +". " + arr.get(i).toString());
        }
    }

    /**
     * Prints the task added to the list.
     * @param task
     */
    public void printNewList(Task task) {
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list.");

    }

    /**
     * Function to add a todo into the arr.
     * @param input
     */
    public void addTodo(String input) {
        Todo todo = new Todo(input);
        arr.add(todo);
        printNewList(todo);
    }

    /**
     * Function to add a deadline to the arr.
     * @param input
     * @param time
     * @throws AllyException
     */
    public void addDeadline(String input, String time) throws AllyException {
        Deadline ddline = new Deadline(input, time);
        arr.add(ddline);
        printNewList(ddline);
    }

    /**
     * Function to add an event to the list or array.
     * @param input
     * @param from
     * @param to
     * @throws AllyException
     */
    public void addEvent(String input, String from, String to) throws AllyException {
        Event event = new Event(input, from, to);
        arr.add(event);
        printNewList(event);
    }

    /**
     * Function to delete a task from the list or array.
     * @param index
     * @throws AllyException
     */
    public void deleteElement(int index) throws AllyException {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + arr.get(index));
        arr.remove(index);
        System.out.println("Now you have " + arr.size() + " tasks in the list.");
    }

}
