package gudetama.storage;

import java.io.*;
import java.util.ArrayList;

import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.tasks.Todo;
import gudetama.tasks.Event;
import gudetama.tasks.Deadline;

/**
 * Represents a storage which stores and retrieves the task list
 */
public class Storage {
    /**
     * The file used for storage
     */
    private File file;
    private String path;

    /**
     * Constructor for Storage
     *
     * @param path File path of the file used for storage
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * Saves a list of tasks to the storage file
     *
     * @param inputs List of tasks to be stored
     */
    public void saveToFile(ArrayList<Task> inputs) {
        try {
            File f = new File("./data/duke.txt");
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            for (Task t : inputs) {
                fw.write(t.store() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving to the file: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the storage file
     *
     * @return Task list that contains the tasks read from the storage file
     */
    public TaskList readFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                String task = line.split(" ")[0];
                String[] description = line.split("\\|");

                if (task.equals("T")){
                    Todo todo = new Todo(description[2].trim(), description[1].trim());
                    taskList.add(todo);
                } else if (task.equals("D")){
                    Deadline deadline = new Deadline(description[2].trim(), description[3].trim(), description[1].trim());
                    taskList.add(deadline);
                } else if (task.equals("E")){
                    String date[] = description[3].trim().split("to");
                    Event event = new Event(description[2].trim(), date[0].trim(), date[1].trim(), description[1].trim());
                    taskList.add(event);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(taskList);
    }
}
