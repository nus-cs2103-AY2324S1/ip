import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static final String FILE_PATH = "duke.txt";
    /**
     * A task list that stores the user's tasks.
     */
    private ArrayList<Task> taskList;
    /**
     * An index that tracks the current newest position in the task list.
     */
    private int index;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
        this.index = 1;
    }

    /**
     * Creates tasks based on the text file and adds them to the task list.
     *
     * @throws FileNotFoundException
     */
    public void loadTaskList() throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<Task> tempList = new ArrayList<>(100);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String eventType = currentLine.substring(4, 5);
            boolean eventDone = currentLine.substring(7, 8).equals("X");
            if (eventType.equals("T")) {
                String eventName = currentLine.substring(10);
                Todo todo = new Todo(eventName, eventDone);
                tempList.add(todo);
            } else if (eventType.equals("D")) {
                String[] strSegments = currentLine.substring(10).split( " By: ");
                String eventName = strSegments[0].trim();
//                int length = strSegments[1].length();
//                String date = strSegments[1].substring(0, length);
                String date = strSegments[1];
                Deadline deadline = new Deadline(eventName, date, eventDone);
                tempList.add(deadline);
            } else {
                String[] strSegments = currentLine.substring(10).split(" From: ");
                String eventName = strSegments[0].substring(0, strSegments[0].length()).trim();
                String[] strSegments2 = strSegments[1].split(" To: ");
                String startDate = strSegments2[0];
                int length = strSegments2[1].length();
                String endDate = strSegments2[1].substring(0, length);
                Event event = new Event(eventName, startDate, endDate, eventDone);
                tempList.add(event);
            }
        }
        for (int i = 0; i < tempList.size(); i++) {
            this.taskList.add(i, tempList.get(i));
        }
        this.index = taskList.size() + 1;
    }

    /**
     * Adds a task to the task list.
     * Prints the name of the task added.
     * Saves the changes to the text file.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        String string = this.index + ". " + task.toString() +"\n";
        try {
            appendToFile(FILE_PATH, string);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.displayableForm());
            System.out.println("Now you have " + this.index + " tasks in the list.");

            this.index++;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Prints the contents of the task list in the order they were added.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i) != null) {
                System.out.println((i+1) + ". " + this.taskList.get(i).displayableForm());
            }
        }
    }

    /**
     * Marks the task with the specified index as done.
     * Prints a notification indicating that the specified task has been marked done.
     * Saves the changes to the text file.
     *
     * @param index The index of the task to be marked as done
     */
    public void markDone(int index) {
        try {
            Task task = this.taskList.get(index - 1);
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.displayableForm());

            writeToFile(FILE_PATH, "");

            if (this.index > 0) {
                for (int i = 0; i < this.index - 1; i++) {
                    appendToFile(FILE_PATH, (i + 1) + ". " + this.taskList.get(i) + "\n");
                }
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! There is no task with this index number.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong!");
        }
    }

    /**
     * Marks the task with the specified index as not done.
     * Prints a notification indicating that the specified task has been marked not done.
     * Saves the changes to the text file.
     *
     * @param index The index of the task to be marked as not done
     */
    public void markNotDone(int index) {
        try {
            Task task = this.taskList.get(index - 1);
            task.setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.displayableForm());

            writeToFile(FILE_PATH, "");

            if (this.index > 0) {
                for (int i = 1; i < this.index - 1; i++) {
                    appendToFile(FILE_PATH, (i + 1) + ". " + this.taskList.get(i) + "\n");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! There is no task with this index number.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong!");
        }
    }
    /**
     * Removes the task with the specified index from the task list.
     * Prints a notification indicating that the specified task has been marked not done.
     * Saves the changes to the text file.
     *
     * @param number The index of the task to be removed.
     */
    public void delete(int number) {
        try {
            this.index = this.taskList.size();
            Task task = this.taskList.get(number - 1);
            this.taskList.remove(number - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.displayableForm());
            System.out.println("Now you have " + (this.index - 1) + " tasks in the list.");

            writeToFile(FILE_PATH, "");

            if (this.taskList.size() > 0) {
                for (int i = 0; i < index - 1; i++) {
                    appendToFile(FILE_PATH, (i + 1) + ". " + this.taskList.get(i) + "\n");
                }
            }

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("☹ OOPS!!! There is no task with this index number.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong!");
        }
    }

    /**
     * Writes a string to the specified text file.
     *
     * @param filePath The path of the file.
     * @param textToAdd The string to be written.
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds a string to the end of the specified text file.
     *
     * @param filePath The path of the file.
     * @param textToAppend The string to be added.
     * @throws IOException
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
