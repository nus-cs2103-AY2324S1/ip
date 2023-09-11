package friday;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Represents a storage system to save and view tasks.
 */
public class Storage {

    private File storage;
    private String filePath;
    /**
     * Constructs a new Storage system.
     *
     * @param filePath Path to the file where tasks will be saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String directoryPath = filePath.substring(0, filePath.lastIndexOf('/'));
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.storage = new File(filePath);
    }

    /**
     * Saves the provided task list to a file.
     *
     * @param taskList String representation of tasks to be saved.
     * @throws IOException If there's an error writing to the file.
     */
    public void saveFile(String taskList) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write(taskList);
        file.close();
    }

    /**
     * Saves the provided note list to a file.
     *
     * @param noteList String representation of notes to be saved.
     * @throws IOException If there's an error writing to the note file.
     */
    public void saveNoteFile(String noteList) throws IOException {
        FileWriter file = new FileWriter(filePath);
        file.write(noteList);
        file.close();
    }
}

