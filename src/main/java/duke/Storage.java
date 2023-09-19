package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private Path dukeFilePath = Paths.get("./data/duke.txt");

    /*
         txt file that contains the contents of the TaskList in a human-readable format
         this file is read in the printFileContents method in Ui.java
     */
    private Path tempFilePath;
    /*
         "./data/temp.txt"
         txt file that contains the contents of the TaskList in binary format
         this file is used to store TaskList contents that will persist with consecutive runs of the application
     */
    private Path undoFilePath = Paths.get("./data/undo.txt");

    /*
         txt file that contains the contents of the OLD TaskList in binary format
         contents of this file is read when 'undo' command is called, so the old TaskList will be obtained
    */
    public Storage(Path filePath) {
        this.tempFilePath = filePath;
    }

    /**
     * This function reads the ArrayList(Task) object that is stored in memory and returns it
     *
     * @return An ArrayList that contains Task objects
     * @throws ClassNotFoundException If inputStream.readObject() does not find anything
     * @throws IOException            If unable to find temp.txt file
     */
    public ArrayList<Task> load() throws ClassNotFoundException, IOException {
        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(tempFilePath));
        /*
            The only object that is written to OutputStream is the ArrayList<duke.Task>, as shown in the
            updateFile method in duke.Duke.java. Thus, only an object of run type ArrayList<duke.Task> can be
            read from the InputStream, so it is safe to cast it to (ArrayList<duke.Task>)
         */
        @SuppressWarnings("unchecked")
        ArrayList<Task> loadedTasks = (ArrayList<Task>) inputStream.readObject();
        return loadedTasks;
    }

    /**
     * Everytime there is a change in tasks, 2 things happen
     * 1. The duke.txt file is cleared, TaskList is scanned and the entire duke.txt file is rewritten
     * 2. ObjectOutputStream scans TaskList, serializes each Task into binary format, and writes them
     * to "data/temp.txt". This allows us to save the TaskList to a file and retrieve it when the program
     * is run again
     */
    public void updateFile(TaskList tasks, boolean shouldUpdate) {
        try {
            if (shouldUpdate) {
                copyTempToUndo();
                // copies the contents of temp.txt to undo.txt BEFORE temp.txt is updated to reflect new TaskList
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(tempFilePath))) {
                outputStream.writeObject(tasks.getTasks());
                // writes the ArrayList(Task) object to the OutputStream

                BufferedWriter writer = Files.newBufferedWriter(dukeFilePath);
                for (int i = 0; i < tasks.getSize(); i++) {
                    writer.write(tasks.getTaskByIndex(i).toFileString() + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the 'undo the most recent command' feature.
     * Copies the contents of temp.txt to undo.txt before every iteration of the updateFile method.
     * @throws IOException If unable to locate source/destination files
     */
    private void copyTempToUndo() throws IOException {
        InputStream is = Files.newInputStream(tempFilePath);
        OutputStream os = Files.newOutputStream(undoFilePath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
    }

    /**
     * This method is called when 'undo' command is ran. Copies contents of undo.txt into temp.txt.
     * This updates temp.txt to contain the old TaskList
     * @throws IOException If source or destination files cannot be found
     */
    public void copyUndoToTemp() throws IOException {
        InputStream is = Files.newInputStream(undoFilePath);
        OutputStream os = Files.newOutputStream(tempFilePath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
    }
}
