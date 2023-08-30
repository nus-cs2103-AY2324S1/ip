import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private List<Task> list = new ArrayList<>();

    public Storage() {}

    /** Reads data.txt file and stores the task into a list.
     *  If data.txt file does not exist, it will attempt to create a data.txt file
     */
    public void readFile() {
        try {
            File file = new File("./src/main/java/data.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String task = fileReader.nextLine();
                String[] taskDescription = task.split(" ", 3);
                String taskType = taskDescription[0];
                boolean isDone = taskDescription[1].equals("1");
                String description = taskDescription[2];
                switch (taskType) {
                case "T":
                    Task toDoTask = new ToDo(description, isDone);
                    this.list.add(toDoTask);
                    break;
                case "D":
                    String[] splitDeadline = description.split("/by", 2);
                    String deadlineTaskDescription = splitDeadline[0];
                    String deadline = splitDeadline[1];
                    Task deadlineTask = new Deadline(deadlineTaskDescription, isDone, deadline);
                    this.list.add(deadlineTask);
                    break;
                case "E":
                    String[] splitEvent = description.split("/from", 2);
                    String eventTaskDescription = splitEvent[0];
                    String[] timings = splitEvent[1].split("/to", 2);
                    String start = timings[0];
                    String end = timings[1];
                    Task eventTask = new Event(eventTaskDescription, isDone, start, end);
                    this.list.add(eventTask);
                    break;
                }
            }
            fileReader.close();
        } catch (FileNotFoundException error) {
            createFile();
        }
    }

    /** Creates a file named data.txt. */
    public void createFile() {
        try {
            File file = new File("./src/main/java/data.txt");
            if (!file.createNewFile()) {
                readFile();
            }
        } catch (IOException error) {
           throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }

    /** Goes through all task stored in list and updates the hard drive. */
    public void updateStorage() {
        try {
            FileWriter writer = new FileWriter("./src/main/java/data.txt");
            for (Task task: this.list) {
                String description = task.getStorageDescription();
                writer.write(description + "\n");
            }
            writer.close();
        } catch (IOException error) {
            throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }

    /** Stores the task into the task list.
     *
      * @param task Task to be added to list.
     */
    public void store(Task task) {
        this.list.add(task);
    }

    /** Return length of the list.
     *
     * @return Length of list.
     */
    public int length() {
        return this.list.size();
    }

    /** Get the task at the specific index of the list.
     *
     * @param index Position of the task in the list.
     * @return The task at the index position.
     */
    public Task retrieve(int index) {
        return this.list.get(index);
    }

    /** Removes the task specified by the index inputed.
     *
     * @param index Position of the task that is to be removed.
     */
    public void delete(int index) {
        this.list.remove(index);
    }
}
