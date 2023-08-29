package util;

import main.Main;
import task.Task;
import task.TaskList;

import java.io.*;

public class FileUtil {
    private static final String path = "data.bin";
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
            Main.getInstance().say("Unable to save tasks to file: " + e.getMessage());
        }
    }

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
            Main.getInstance().say("Unable to read tasks from file: " + e.getMessage());
            return new TaskList();
        }
    }
}
