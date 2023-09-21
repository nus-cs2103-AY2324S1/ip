package rock.storage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import rock.client.Rock;
import rock.storage.exceptions.StorageException;
import rock.tasks.TaskList;

/**
 * Used to handle saving
 * and loading files
 * @author Alvis Ng (supermii2)
 */
public class Storage {
    /** Object representation of file used to save/load data */
    private File saveFile;
    /** Chatbot client*/
    private Rock client;
    /**
     * Constructor for storage object
     * @param file File used to save and load data
     * @param client Chatbot client
     */
    public Storage(File file, Rock client) {
        this.client = client;
        this.saveFile = file;
        try {
            createSaveFile(client);
            loadSaveFile();
        } catch (StorageException e) {
            client.sendWarning(e.getMessage());
        }
    }
    /**
     * Creates a save file if one doesn't exist
     * @throws StorageException Thrown if save file directory is unreachable
     */
    private void createSaveFile(Rock client) throws StorageException {
        File saveFile = this.saveFile;
        try {
            saveFile.getParentFile().mkdirs();
            if (saveFile.createNewFile()) {
                client.sendWarning("No save file found! Creating new save file.");
                saveSaveFile(new TaskList());
            }
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to create save file!");
        }
    }
    /**
     * Saves existing task list to save file
     * @throws StorageException Thrown if no save file can be found to save to
     */
    public void saveSaveFile(TaskList tl) throws StorageException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tl);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to find save file!");
        }
    }
    /**
     * Load a save file from the file.
     * @throws StorageException Thrown if the save file is corrupted are cannot be found
     */
    private void loadSaveFile() throws StorageException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object saveObj = objectInputStream.readObject();
            if (saveObj instanceof TaskList) {
                this.client.setTaskList((TaskList) saveObj);
                objectInputStream.close();
            } else {
                objectInputStream.close();
                throw new StorageException("WARNING: Save File corrupted. Please delete save file!");
            }
        } catch (IOException e) {
            throw new StorageException("WARNING: Failed to load save file!");
        } catch (ClassNotFoundException e) {
            throw new StorageException("WARNING: Class not found! Rock is likely corrupted. Please reinstall!");
        }
    }
}
