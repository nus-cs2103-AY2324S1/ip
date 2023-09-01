import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class AllyList {
    ArrayList<Task> arr;
    String filePath;
    public AllyList(String filePath) throws AllyException {
        arr = new ArrayList<>(100);
        this.filePath = filePath;
        try {
            load();
        } catch (AllyException e) {
            throw new AllyException("Unable to load");
        }
    }

    public void createFile() throws AllyException {
        File f = new File(filePath);
        File dir = new File(f.getParent());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new AllyException("Ohnos, you can't create the file :-(");
        }
    }

    public void appendToFile(Task task) throws AllyException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(task.formatFile());
            fw.close();
        } catch (IOException e) {
            throw new AllyException("Can't write your file");
        }
    }

    public Task readData(String data) {
        String[] splits = data.split(" | ");
        Task savedTasks = null;

        if (splits[0].equals("T")) {
            savedTasks = new Todo(splits[2]);
        } else if (splits[0].equals("D")) {
            savedTasks = new Deadline(splits[2], splits[3]);
        } else if (splits[0].equals("E")) {
            savedTasks = new Event(splits[2], splits[3], splits[4]);
        }

        if (splits[1].equals("1")) {
            savedTasks.setMarked();
        }
        return savedTasks;
    }

    public void load() throws AllyException {
        ArrayList<Task> loadTasks = new ArrayList<>(100);
        createFile();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String line = s.nextLine();
                loadTasks.add(readData(line));
            }
        } catch (FileNotFoundException e) {
            throw new AllyException("Not able to scan!");
        }
    }


    /**
     * Function to add tasks from the input into the arraylist.
     * @param str
     */
    public void addElements(String str) throws AllyException {
        Task task = new Task(str);
        arr.add(task);
        appendToFile(task);
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
    public void addTodo(String input) throws AllyException {
        Todo todo = new Todo(input);
        arr.add(todo);
        appendToFile(todo);
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
        appendToFile(ddline);
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
        appendToFile(event);
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
