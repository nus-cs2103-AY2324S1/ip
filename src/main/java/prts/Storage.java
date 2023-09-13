package prts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The class responsible for dealing with saving and loading the state of the TaskList to and from
 * a save file on the user's hard disk.
 */
public class Storage {

    private File saveFile;

    /**
     * Constructs a Storage object. In addition, verifies if the specified file path for the save file
     * exists. If it does not exist, creates the necessary directory/directories and file.
     * @param fileName The name of the desired save file.
     * @param directories One or more desired directory names, to specify the path where the file
     *                    should be stored in. These extend off of <code>System.getProperty(user.dir)
     *                    </code>.
     */
    public Storage(String fileName, String[] directories) {

        Path fileDirectoryPath = Path.of(System.getProperty("user.dir"));
        File saveFileDirectory;

        for (String directory : directories) {
            fileDirectoryPath = Paths.get(fileDirectoryPath.toString(), directory);
            saveFileDirectory = new File(fileDirectoryPath.toUri());
            if (saveFileDirectory.exists()) {
                continue;
            }
            if (saveFileDirectory.mkdir()) {
                System.out.println("Directory " + fileDirectoryPath.toString() + " created.");
            }
        }

        Path filePath = Paths.get(fileDirectoryPath.toString(), fileName);
        saveFile = new File(filePath.toUri());
        if (!saveFile.exists()) {
            try {
                if (saveFile.createNewFile()) {
                    System.out.println("Save file could not be located, new file created");
                }
            } catch (IOException e) {
                System.out.println("Error creating save file: " + e.getMessage());
            }

        }

    }

    /**
     * Attempts to load the TaskList from the save file.
     * @return The loaded TaskList.
     * @throws CreateNewSaveException If the file could not be located or read, and a new save file
     *         was created.
     * @throws NewSaveFailedException If the file could not be located or read, and a new save file
     *         could not be created.
     */
    public TaskList load() throws CreateNewSaveException, NewSaveFailedException {

        if (saveFile.length() == 0) {
            return new TaskList();
        }

        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList tasks = (TaskList) ois.readObject();
            ois.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            saveFile.delete();
            try {
                saveFile.createNewFile();
                throw new CreateNewSaveException();
            } catch (IOException ee) {
                throw new NewSaveFailedException();
            }
        }
    }

    /**
     * Attempts to save the TaskList to the save file.
     * @param tasks The current TaskList.
     * @throws SaveToFileException If the TaskList could not be successfully saved to the file.
     */
    public void save(TaskList tasks) throws SaveToFileException {
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            throw new SaveToFileException();
        }
    }

}
