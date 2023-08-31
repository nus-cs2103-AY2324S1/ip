package storage;

import tasks.TaskList;
import client.Rock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Used to handle saving
 * and loading files
 * 
 * @author Alvis Ng (supermii2)
 */
public class Storage {
    /** Object representation of file */
    File saveFile;
    TaskList taskList;
    Rock client;
    public Storage(File file, TaskList taskList, Rock client) {
        this.client = client;
        this.saveFile = file;
        this.taskList = taskList;
        try {
            createSaveFile();
            loadSaveFile(taskList);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createSaveFile() throws StorageException {
        File saveFile = this.saveFile;
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to create save file!");
        }
    }
    public void saveSaveFile() throws StorageException{
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.client.taskList);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new StorageException("WARNING: Unable to find save file!");
        }
    }
    public void loadSaveFile(TaskList list) throws StorageException {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object saveObj = objectInputStream.readObject();
            if (saveObj instanceof TaskList) {
                this.client.taskList = (TaskList) saveObj;
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
