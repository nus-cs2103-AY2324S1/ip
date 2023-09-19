package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    /**
     * This function reads the ArrayList(Task) object that is stored in temp.txt and returns it
     *
     * @return An ArrayList that contains Task objects
     * @throws ClassNotFoundException If InputStream.readObject() does not find anything
     * @throws IOException            If unable to find temp.txt file
     */
    public ArrayList<Task> load() throws ClassNotFoundException, IOException {
        ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Duke.CURRENT_TASKLIST_FILE_PATH));
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
     * Updates duke.txt and temp.txt whenever the user inputs a command
     */
    public void updateFile(TaskList tasks, boolean shouldUpdate) {
        try {
            if (shouldUpdate) {
                copyTempToUndo();
                // saves the contents of temp.txt into undo.txt BEFORE temp.txt is updated to reflect new TaskList
            }
            try (ObjectOutputStream outputStream =
                         new ObjectOutputStream(Files.newOutputStream(Duke.CURRENT_TASKLIST_FILE_PATH))) {
                outputStream.writeObject(tasks.getTasks());
                // writes the ArrayList(Task) object to the OutputStream

                BufferedWriter writer = Files.newBufferedWriter(Duke.DUKE_FILE_PATH);
                for (int i = 0; i < tasks.getSize(); i++) {
                    writer.write(tasks.getTaskByIndex(i).toFileString() + "\n");
                    // does not append text to duke.txt, but simply overwrites
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the contents of temp.txt into undo.txt BEFORE temp.txt is updated to reflect new TaskList
     * @throws IOException If unable to locate source/destination files
     */
    private void copyTempToUndo() throws IOException {
        InputStream is = Files.newInputStream(Duke.CURRENT_TASKLIST_FILE_PATH);
        OutputStream os = Files.newOutputStream(Duke.OLD_TASKLIST_FILE_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
    }

    /**
     * Copies contents of undo.txt into temp.txt. This updates temp.txt to contain the old TaskList
     * @throws IOException If source or destination files cannot be found
     */
    public void copyUndoToTemp() throws IOException {
        InputStream is = Files.newInputStream(Duke.OLD_TASKLIST_FILE_PATH);
        OutputStream os = Files.newOutputStream(Duke.CURRENT_TASKLIST_FILE_PATH);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
    }
}
