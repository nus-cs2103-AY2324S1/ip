package duke;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
