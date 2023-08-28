import java.nio.file.Path;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    Path savePath;
    TaskList taskList;
    Storage(Path path, TaskList taskList) {
        this.savePath = path;
        this.taskList = taskList;
        createSaveFile();
        loadSaveFile(taskList);
    }
    public void createSaveFile() throws StorageException {
        File saveFile = this.savePath.toFile();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to create save file!");
        }
    }
    public void saveSaveFile() throws StorageException{
        File saveFile = savePath.toFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(taskList);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to find save file!");
        }
    }
    public void loadSaveFile(TaskList list) throws StorageException {
        File saveFile = savePath.toFile();
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object saveObj = objectInputStream.readObject();
            if (saveObj instanceof TaskList) {
                taskList = (TaskList) saveObj;
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
