import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String fileName;
    private String[] directories;
    private Path filePath;
    private File saveFile;

    public Storage(String fileName, String[] directories) {
        this.fileName = fileName;
        this.directories = directories;

        filePath = Path.of(System.getProperty("user.dir"));

        for (String directory : directories) {
            filePath = Paths.get(filePath.toString(), directory);
            saveFile = new File(filePath.toUri());
            if (!saveFile.exists()) {
                if (saveFile.mkdir()) {
                    System.out.println("Directory " + filePath.toString() + " created.");
                }
            }
        }

        filePath = Paths.get(filePath.toString(), fileName);
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

    public TaskList load() throws DukeException {

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
                System.out.println("File failed to load, created new save file.");
                return new TaskList();
            } catch (IOException ee) {
                throw new DukeException("Failed to load file, new save file could not be created.");
            }
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (IOException e) {
            throw new DukeException("Saving to file failed");
        }
    }

}
