package util;

import main.Main;
import task.Task;
import task.TaskList;

import java.io.*;

public class Storage {
    private static final String path = "data.bin";

    /**
     * Serializes all tasks into the data file.
     */
    public static void saveTasksToFile(){
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Main.getInstance().getTaskList().iterate((index, task) -> {
                try {
                    oos.writeObject(task);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            oos.close();
            fos.close();
        }
        catch(Exception e){
            Main.getInstance().getUi().say("Unable to save tasks to file: " + e.getMessage());
        }
    }

    /**
     * Create the TaskList instance from deserializing the data file
     * @return The TaskList containing all deserialized tasks form the data file,
     * or a TaskList instance containing no tasks if the data file not exists or can not be read.
     */
    public static TaskList createTaskListFromFile() {
        File file = new File(path);
        if(!file.exists()){
            return new TaskList();
        }
        TaskList tl = new TaskList();
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                try{
                    tl.addTask((Task)ois.readObject());
                }
                catch (EOFException e){
                    ois.close();
                    fis.close();
                    break;
                }
            }
            return tl;
        }
        catch (Exception e){
            System.out.println("Warning: Unable to read tasks from file. " + e.getMessage());
            return new TaskList();
        }
    }
}
