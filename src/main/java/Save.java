import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {
    public static Path FILE_PATH = Paths.get("data", "tasks.ser");
    public static void createSaveFile() {
        File saveFile = FILE_PATH.toFile();
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            Rock.say("WARNING: Unable to create save file!");
        }
    }
    public static void saveSaveFile() {
        File saveFile = FILE_PATH.toFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(Rock.taskList);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            Rock.say("WARNING: Unable to find save file!");
        }
    }
    public static void loadSaveFile(TaskList list) {
        File saveFile = FILE_PATH.toFile();
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object saveObj = objectInputStream.readObject();
            if (saveObj instanceof TaskList) {
                Rock.taskList = (TaskList) saveObj;
                objectInputStream.close(); 
            } else {
                objectInputStream.close();
                throw new SaveCorruptedException();
            }
        } catch (IOException e) {
            Rock.say("WARNING: Failed to load save file!");
        } catch (ClassNotFoundException e) {
            Rock.say("WARNING: Class not found! Rock is likely corrupted. Please reinstall!");
        } catch (SaveCorruptedException e) {
            Rock.say("WARNING: Save File corrupted. Please delete save file!");
        }
    }
    public static void initSave() {
        createSaveFile();
        loadSaveFile(Rock.taskList);
    }
}
