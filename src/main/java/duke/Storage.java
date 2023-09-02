package duke;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    static String dukeFilePath = "data/duke.txt";
    private String filePath; // "data/temp.txt"
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This function reads an ArrayList<duke.Task> object that is stored in memory and returns it
     *
     * @return an ArrayList<duke.Task>
     * @throws DukeException this part might not be needed
     * @throws ClassNotFoundException if inputStream.readObject() does not find anything
     * @throws IOException if "data/temp.txt" does not exist/it is empty (not sure which one or both?)
     */
    public ArrayList<Task> load() throws DukeException, ClassNotFoundException, IOException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
        /*
            The only object that is written to OutputStream is the ArrayList<duke.Task>, as shown in the
            updateFile method in duke.Duke.java. Thus, only an object of run type ArrayList<duke.Task> can be
            read from the InputStream, so it is safe to cast it to (ArrayList<duke.Task>)
         */
        @SuppressWarnings("unchecked")
        ArrayList<Task> loadedTasks = (ArrayList<Task>) inputStream.readObject();
        if (loadedTasks.isEmpty()) {
            // actually this part might be unnecessary
            throw new DukeException("currently there is no task in your Tasks");
        }
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(loadedTasks);
        return tasks;
    }
    /**
     * Everytime there is a change in tasks, 2 things happen
     * 1. The duke.txt file is cleared, tasks is scanned and the entire duke.txt file is rewritten
     * 2. ObjectOutputStream scans tasks, serializes each duke.Task into binary format, and writes them
     * to "data/temp.txt". This allows us to save the duke.TaskList to a file and retrieve it when the program
     * is run again
     *
     * Initially, I did a writeToFile method where everytime a duke.Task is added to taskArray, write
     * the new task to duke.txt. However, this may cause some problems when it comes to updating
     * or deleting tasks from the file, so I changed the implementation to rewriting the entire txt
     * file everytime there is a change to the list. This causes a longer run time but since this mod
     * is not about run time, it should be fine.
     */
    public void updateFile(TaskList tasks) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(tasks.getTasks());
            // writes the ArrayList<duke.Task> object to the OutputStream

            FileWriter fw = new FileWriter(dukeFilePath);
            // ^ the above line is ABSOLUTELY NECESSARY!!! do not delete
            // Clears the existing content by opening in write mode and immediately closing
            fw = new FileWriter(dukeFilePath, true);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ewq2" + e.getMessage());
        }
    }
}
